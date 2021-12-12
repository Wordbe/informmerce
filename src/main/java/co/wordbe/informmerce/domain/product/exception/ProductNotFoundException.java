package co.wordbe.informmerce.domain.product.exception;

import co.wordbe.informmerce.domain.common.exception.CommonException;
import co.wordbe.informmerce.domain.common.enums.ErrorType;

public class ProductNotFoundException extends CommonException {
    public ProductNotFoundException(ErrorType code) { super(code); }
}
