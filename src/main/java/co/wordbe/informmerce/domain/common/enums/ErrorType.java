package co.wordbe.informmerce.domain.common.enums;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String getCode();
    String getMessage();
    HttpStatus getStatus();
}
