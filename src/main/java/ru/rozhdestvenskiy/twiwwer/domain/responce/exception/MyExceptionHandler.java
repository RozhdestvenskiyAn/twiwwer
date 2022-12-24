package ru.rozhdestvenskiy.twiwwer.domain.responce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;
import ru.rozhdestvenskiy.twiwwer.domain.responce.error.Error;
import ru.rozhdestvenskiy.twiwwer.domain.responce.error.ErrorResponse;

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
}
