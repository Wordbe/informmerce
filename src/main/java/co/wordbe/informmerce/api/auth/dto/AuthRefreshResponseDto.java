package co.wordbe.informmerce.api.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthRefreshResponseDto {
    private final String accessToken;
}
