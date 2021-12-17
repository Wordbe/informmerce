package co.wordbe.informmerce.domain.auth.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;

public class UnauthorizedException extends CommonException {
    public UnauthorizedException(ErrorType error) {
        super(error);
    }
}
