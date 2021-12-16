package co.wordbe.informmerce.domain.common.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private final ErrorType error;

    @Builder
    public CommonException(ErrorType error) {
        super(error.getMessage());
        this.error = error;
    }
}
