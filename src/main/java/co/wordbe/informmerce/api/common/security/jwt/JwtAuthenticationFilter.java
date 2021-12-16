package co.wordbe.informmerce.api.common.security.jwt;

import co.wordbe.informmerce.api.common.dto.ErrorResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtManager jwtManager;
    private final Gson gson;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = jwtManager.resolveAccessToken((HttpServletRequest) request);
        if (!StringUtils.hasText(accessToken)) {
            // 인증이 필요하지 않은 요청도 있으니, 이 경우 그대로 다음 필터로 전달한다.
            chain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = jwtManager.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("JWT 인증 실패", e);

            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponse errorResponse = ErrorResponse.of(e.getMessage(), HttpStatus.UNAUTHORIZED.name());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            gson.toJson(errorResponse, response.getWriter());
        }
    }
}
