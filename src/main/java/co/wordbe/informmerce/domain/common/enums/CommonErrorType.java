package co.wordbe.informmerce.domain.common.enums;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommonErrorType implements ErrorType {
    INTERNAL_SERVER_ERROR("[인포머스] 서버 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("[인포머스] 토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus status;

    @Override public String getCode() { return this.name(); }
    @Override public String getMessage() {
        return message;
    }
    @Override public HttpStatus getStatus() { return status; }
}
