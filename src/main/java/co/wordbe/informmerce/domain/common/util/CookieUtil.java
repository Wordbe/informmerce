package co.wordbe.informmerce.domain.common.util;

import co.wordbe.informmerce.api.common.security.jwt.JwtManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CookieUtil {

    public static void addRefreshToken(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true); // https 일 때만, 운영에 반영
        cookie.setMaxAge(60 * 60 * 24 * JwtManager.REFRESH_TOKEN_VALID_DAYS);
        response.addCookie(cookie);
    }

    public static String cookieValueByKey(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        List<String> cookieValues = Arrays.stream(cookies)
                .filter(cookie -> key.equals(cookie.getName()))
                .map(Cookie::getValue)
                .collect(toList());

        if (cookieValues.isEmpty()) {
            return null;
        }

        return cookieValues.get(0);
    }

    public static void removeCookieByKey(String key, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
