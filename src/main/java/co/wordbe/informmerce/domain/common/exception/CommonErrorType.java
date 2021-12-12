package co.wordbe.informmerce.domain.common.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommonErrorType implements ErrorType {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    @Override public String getCode() { return this.name(); }
    @Override public String getMessage() {
        return message;
    }
    @Override public HttpStatus getStatus() { return status; }
}
