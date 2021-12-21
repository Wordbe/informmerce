package co.wordbe.informmerce.api.common.security.jwt;

import co.wordbe.informmerce.api.common.security.jwt.dto.RefreshTokenPayload;
import co.wordbe.informmerce.domain.common.util.LocalDateTimeUtil;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Component
public class JwtManager {
    public static final int ACCESS_TOKEN_VALID_MINUTES = 60;
    public static final int REFRESH_TOKEN_VALID_DAYS = 14;
    private final SecretKey ACCESS_TOKEN_SECRET_KEY;
    private final Gson gson;

    public JwtManager(@Value("${jwt.access-token-secret-key}")
                      String accessTokenSecretKey,
                      Gson gson) {
        this.ACCESS_TOKEN_SECRET_KEY = generateHmacShaKey(accessTokenSecretKey);
        this.gson = gson;
    }

    public static SecretKey generateHmacShaKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createAccessToken(MemberAttribute member, LocalDateTime issuedAt) {
        return createJwt(member, issuedAt, issuedAt.plusMinutes(ACCESS_TOKEN_VALID_MINUTES), ACCESS_TOKEN_SECRET_KEY);
    }

    public String createRefreshToken(MemberAttribute member, LocalDateTime issuedAt, SecretKey secretKey) {
        return createJwt(member, issuedAt, issuedAt.plusDays(REFRESH_TOKEN_VALID_DAYS), secretKey);
    }

    public String createJwt(MemberAttribute member, LocalDateTime issuedAt, LocalDateTime expiresAt, SecretKey secretKey) {
        return Jwts.builder()
                .setAudience(member.getEmail())
                .setIssuedAt(LocalDateTimeUtil.date(issuedAt))
                .setExpiration(LocalDateTimeUtil.date(expiresAt))
                .claim("id", member.getId())
                .claim("roles", member.getRoles())
                .claim("provider", member.getProvider().name())
                .signWith(secretKey)
                .compact();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null ||
            !authorization.startsWith("Bearer ")) {
            return null;
        }

        return authorization.substring(7);
    }

    public Jws<Claims> getClaims(String jwt) {
        return getClaimsWithSecretKey(jwt, ACCESS_TOKEN_SECRET_KEY);
    }

    public Authentication getAuthentication(String accessToken) {
        Jws<Claims> claims = getClaims(accessToken);
        Claims payload = claims.getBody();
        List<String> roles = (List<String>) payload.get("roles");

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());

        MemberEntity member = MemberEntity.builder()
                .id(Long.valueOf(payload.get("id", String.class)))
                .email(payload.getAudience())
                .provider(MemberAuthProvider.valueOf(payload.get("provider", String.class).toUpperCase()))
                .build();

        return new UsernamePasswordAuthenticationToken(member, null, authorities);
    }

    public Jws<Claims> getClaimsWithSecretKey(String jwt, SecretKey secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt);
    }

    public RefreshTokenPayload getClaimsWithoutSecretKey(String jwt) {
        String[] chunks = jwt.split("\\.");
        String payload = new String(Decoders.BASE64.decode(chunks[1]));

        return gson.fromJson(payload, RefreshTokenPayload.class);
    }
}
