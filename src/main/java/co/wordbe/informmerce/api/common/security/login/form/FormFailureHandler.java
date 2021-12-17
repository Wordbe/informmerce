package co.wordbe.informmerce.api.common.security.login.form;

import co.wordbe.informmerce.api.common.dto.ErrorResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FormFailureHandler implements AuthenticationFailureHandler {
    private final Gson gson;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(e.getMessage(), HttpStatus.UNAUTHORIZED.name());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(errorResponse));
    }
}
