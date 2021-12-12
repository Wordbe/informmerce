package co.wordbe.informmerce.api.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final String message;
    private final String code;

    public static ErrorResponse of(String message, String code) {
        return new ErrorResponse(message, code);
    }
}
