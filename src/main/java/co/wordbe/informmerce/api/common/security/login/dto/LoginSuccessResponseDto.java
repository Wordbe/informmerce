package co.wordbe.informmerce.api.common.security.login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginSuccessResponseDto {
    private final String accessToken;
}
