package co.wordbe.informmerce.api.common.controller;

import co.wordbe.informmerce.api.common.dto.ErrorResponse;
import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException e) {
        log.error("[CommonException]", e);
        ErrorType error = e.getError();
        return ResponseEntity
                .status(error.getStatus())
                .body(ErrorResponse.of(error.getMessage(), error.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnhandledException(Exception e) {
        log.error("[UnhandledException]", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(e.getMessage(), CommonErrorType.INTERNAL_SERVER_ERROR.getCode()));
    }
}
