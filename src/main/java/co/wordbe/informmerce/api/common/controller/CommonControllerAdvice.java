package co.wordbe.informmerce.api.common.controller;

import co.wordbe.informmerce.api.common.dto.ErrorResponse;
import co.wordbe.informmerce.domain.common.enums.ErrorType;
import co.wordbe.informmerce.domain.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    /**
     * 도메인 예외 처리
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleException(CommonException e) {
        log.error("[CommonException]", e);
        ErrorType error = e.getError();
        return ResponseEntity
                .status(error.getStatus())
                .body(ErrorResponse.of(error.getMessage(), error.getCode()));
    }

    /**
     * 스프링 시큐리티 인증 실패
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(InsufficientAuthenticationException e) {
        log.error("[InsufficientAuthenticationException]", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(e.getMessage(), HttpStatus.UNAUTHORIZED.name()));
    }

    /**
     * 유효성 검사 실패
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException]", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getMessage(), HttpStatus.BAD_REQUEST.name()));
    }

    /**
     * 다뤄지지 않은 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("[UnhandledException]", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }
}
