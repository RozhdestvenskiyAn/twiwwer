package ru.rozhdestvenskiy.twiwwer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Error;
import ru.rozhdestvenskiy.twiwwer.domain.responce.ErrorResponse;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedErrorException(Exception ex) {
        ex.printStackTrace();
        log.error("Interval server error: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .code(Code.INTERNAL_SERVER_ERROR)
                        .message("Interval server error")
                        .build())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException ex){
        log.error("Common error: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .build())
                .build(), ex.getHttpStatus());
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex){
        log.error("Bad credentials error: {}", ex.toString());
        return new ResponseEntity<>(ErrorResponse.builder()
                .error(Error.builder()
                        .code(Code.BAD_CREDENTIALS)
                        .message(ex.getMessage())
                        .build())
                .build(), HttpStatus.UNAUTHORIZED);
    }
}
