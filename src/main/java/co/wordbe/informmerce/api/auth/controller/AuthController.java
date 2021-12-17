package co.wordbe.informmerce.api.auth.controller;

import co.wordbe.informmerce.api.auth.dto.AuthRefreshResponseDto;
import co.wordbe.informmerce.api.auth.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthFacade authFacade;

    @PostMapping("/v1/auth/refresh")
    public AuthRefreshResponseDto refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        return authFacade.refreshAccessToken(request, response);
    }

    @PostMapping("/v1/auth/logout")
    public void logout(HttpServletResponse response) {
        authFacade.logout(response);
    }
}
