package co.wordbe.informmerce.api.common.security.login.form;

import co.wordbe.informmerce.api.common.security.jwt.JwtManager;
import co.wordbe.informmerce.api.common.security.login.dto.LoginSuccessResponseDto;
import co.wordbe.informmerce.api.member.mapper.MemberMapper;
import co.wordbe.informmerce.domain.common.util.CookieUtil;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class FormSuccessHandler implements AuthenticationSuccessHandler {
    private final FindMemberService findMemberService;
    private final MemberMapper memberMapper;
    private final JwtManager jwtManager;
    private final Gson gson;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        MemberEntity principal = (MemberEntity) authentication.getPrincipal();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        MemberEntity memberEntity = findMemberService.findByEmailAndProvider(principal.getEmail(), principal.getProvider());
        LocalDateTime now = LocalDateTime.now();
        memberEntity.updateLastLoginAt(now);

        boolean notExpired = memberEntity.getSecretKeyExpiredAt() != null && memberEntity.getSecretKeyExpiredAt().isAfter(now);
        if (!notExpired) {
            memberEntity.updateSecretKey(100);
            memberEntity.updateSecretKeyExpiredAt(now.plusDays(JwtManager.REFRESH_TOKEN_VALID_DAYS));
        }

        MemberAttribute memberAttribute = memberMapper.toAttribute(memberEntity);
        memberAttribute.setRoles(roles);

        setRefreshTokenAtCookie(response, memberEntity, now, memberAttribute);
        setAccessTokenAtResponseBody(response, now, memberAttribute);
    }

    private void setRefreshTokenAtCookie(HttpServletResponse response, MemberEntity memberEntity, LocalDateTime now, MemberAttribute memberAttribute) {
        String refreshToken = jwtManager.createRefreshToken(
                memberAttribute,
                now,
                JwtManager.generateHmacShaKey(memberEntity.getSecretKey()));
        CookieUtil.addRefreshToken(response, refreshToken);
    }

    private void setAccessTokenAtResponseBody(HttpServletResponse response, LocalDateTime now, MemberAttribute memberAttribute) throws IOException {
        String accessToken = jwtManager.createAccessToken(memberAttribute, now);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        gson.toJson(new LoginSuccessResponseDto(accessToken), response.getWriter());
    }
}
