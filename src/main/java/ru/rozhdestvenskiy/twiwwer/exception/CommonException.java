package ru.rozhdestvenskiy.twiwwer.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;

import javax.naming.AuthenticationException;

@Data
@Builder
public class CommonException extends RuntimeException {
    private final Code code;
    private final String message;
    private final HttpStatus httpStatus;
}
