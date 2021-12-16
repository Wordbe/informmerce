package co.wordbe.informmerce.domain.common.util;

import co.wordbe.informmerce.api.common.security.jwt.JwtManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addRefreshToken(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); // https 일 때만, 운영에 반영
        cookie.setMaxAge(60 * 60 * 24 * JwtManager.REFRESH_TOKEN_VALID_DAYS);
        response.addCookie(cookie);
    }
}
