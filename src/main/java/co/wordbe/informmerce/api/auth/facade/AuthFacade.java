package co.wordbe.informmerce.api.auth.facade;

import co.wordbe.informmerce.api.auth.dto.AuthRefreshResponseDto;
import co.wordbe.informmerce.api.common.security.jwt.dto.RefreshTokenPayload;
import co.wordbe.informmerce.api.common.security.jwt.JwtManager;
import co.wordbe.informmerce.api.member.mapper.MemberMapper;
import co.wordbe.informmerce.domain.auth.enums.AuthErrorType;
import co.wordbe.informmerce.domain.auth.exception.RefreshTokenInValidException;
import co.wordbe.informmerce.domain.common.util.CookieUtil;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import co.wordbe.informmerce.domain.member.service.FindMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthFacade {
    private final FindMemberService findMemberService;
    private final JwtManager jwtManager;
    private final MemberMapper memberMapper;

    public AuthRefreshResponseDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = CookieUtil.cookieValueByKey("refreshToken", request);
        if (!StringUtils.hasText(refreshToken)) {
            throw new RefreshTokenInValidException(AuthErrorType.REFRESH_TOKEN_NOT_EXIST);
        }

        RefreshTokenPayload refreshTokenPayload = jwtManager.getClaimsWithoutSecretKey(refreshToken);
        if (refreshTokenPayload.getAud() == null ||
            refreshTokenPayload.getProvider() == null) {
            CookieUtil.removeCookieByKey("refreshToken", response);
            throw new RefreshTokenInValidException(AuthErrorType.REFRESH_TOKEN_INVALID_PAYLOAD);
        }

        MemberEntity member = findMemberService.findByEmailAndProvider(
                refreshTokenPayload.getAud(),
                MemberAuthProvider.valueOf(refreshTokenPayload.getProvider().toUpperCase()));

        try {
            jwtManager.getClaimsWithSecretKey(refreshToken, JwtManager.generateHmacShaKey(member.getSecretKey()));
        } catch (Exception e) {
            CookieUtil.removeCookieByKey("refreshToken", response);
            throw new RefreshTokenInValidException(e, AuthErrorType.REFRESH_TOKEN_INVALID);
        }

        MemberAttribute memberAttribute = memberMapper.toAttribute(member);
        memberAttribute.setRoles(List.of(member.getRole().name()));

        String accessToken = jwtManager.createAccessToken(memberAttribute, LocalDateTime.now());

        return new AuthRefreshResponseDto(accessToken);
    }

    public void logout(HttpServletResponse response) {
        CookieUtil.removeCookieByKey("refreshToken", response);
    }
}
