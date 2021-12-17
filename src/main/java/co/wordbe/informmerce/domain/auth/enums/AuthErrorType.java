package co.wordbe.informmerce.domain.auth.enums;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthErrorType implements ErrorType {
    NOT_LOGIN("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_NOT_EXIST("리프레시 토큰이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_INVALID_PAYLOAD("리프레시 토큰의 페이로드가 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_INVALID("리프레시 토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus status;


    @Override
    public String getCode() { return this.name(); }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
