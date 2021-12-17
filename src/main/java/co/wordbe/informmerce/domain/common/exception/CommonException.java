package co.wordbe.informmerce.domain.common.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatus status;

    public CommonException(ErrorType error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
        this.status = error.getStatus();
    }

    @Builder
    public CommonException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
