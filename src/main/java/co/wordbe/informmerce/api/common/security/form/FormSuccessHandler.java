package co.wordbe.informmerce.api.common.security.form;

import co.wordbe.informmerce.api.common.security.jwt.JwtManager;
import co.wordbe.informmerce.api.member.mapper.MemberMapper;
import co.wordbe.informmerce.domain.common.util.CookieUtil;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class FormSuccessHandler implements AuthenticationSuccessHandler {
    private final FindMemberService findMemberService;
    private final MemberMapper memberMapper;
    private final JwtManager jwtManager;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
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

        String refreshToken = jwtManager.createRefreshToken(
                memberAttribute,
                now,
                JwtManager.generateHmacShaKey(memberEntity.getSecretKey()));
        CookieUtil.addRefreshToken(response, refreshToken);
    }
}
