package co.wordbe.informmerce.api.common.security.login.oauth2;

import co.wordbe.informmerce.api.common.security.jwt.JwtManager;
import co.wordbe.informmerce.api.common.security.login.dto.LoginSuccessResponseDto;
import co.wordbe.informmerce.api.common.security.login.oauth2.dto.OAuth2UserAttributes;
import co.wordbe.informmerce.api.member.mapper.MemberMapper;
import co.wordbe.informmerce.domain.common.util.CookieUtil;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final FindMemberService findMemberService;
    private final JwtManager jwtManager;
    private final Gson gson;
    private final MemberMapper memberMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        MemberAuthProvider provider = MemberAuthProvider.from(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId());
        OAuth2UserAttributes oAuth2UserAttribute = provider.getOAuth2UserAttribute("", oAuth2User.getAttributes());
        String email = oAuth2UserAttribute.getEmail();

        List<String> roles = oAuth2User.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());

        MemberEntity memberEntity = findMemberService.findByEmailAndProvider(email, provider);
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
