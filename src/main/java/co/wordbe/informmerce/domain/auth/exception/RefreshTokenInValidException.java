package co.wordbe.informmerce.domain.auth.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;

public class RefreshTokenInValidException extends CommonException {
    public RefreshTokenInValidException(ErrorType error) {
        super(error);
    }

    public RefreshTokenInValidException(Exception thrownException, ErrorType error) {
        super(error.getCode(), thrownException.getMessage(), error.getStatus());
    }
}
