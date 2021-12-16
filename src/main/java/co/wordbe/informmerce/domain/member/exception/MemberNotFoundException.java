package co.wordbe.informmerce.domain.member.exception;

import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;

public class MemberNotFoundException extends CommonException {
    public MemberNotFoundException(ErrorType error) {
        super(error);
    }
}
