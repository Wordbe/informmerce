package co.wordbe.informmerce.api.common.controller;

import co.wordbe.informmerce.api.common.dto.ErrorResponse;
import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleException(CommonException e) {
        log.error("[CommonException]", e);
        ErrorType error = e.getError();
        return ResponseEntity
                .status(error.getStatus())
                .body(ErrorResponse.of(error.getMessage(), error.getCode()));
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(InsufficientAuthenticationException e) {
        log.error("[InsufficientAuthenticationException]", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(e.getMessage(), HttpStatus.UNAUTHORIZED.name()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("[UnhandledException]", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }
}
