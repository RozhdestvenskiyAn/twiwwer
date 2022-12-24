package ru.rozhdestvenskiy.twiwwer.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;
import ru.rozhdestvenskiy.twiwwer.domain.responce.exception.CommonException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationUtils {

    private final Validator validator;

    public <T> void validationRequest(T req){
        if (req != null){
            Set<ConstraintViolation<T>> result = validator.validate(req);
            if (!result.isEmpty()){
                String resultValidation = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
                log.info("Request json is not valid, errors of validation: {}", resultValidation);
                throw CommonException.builder()
                        .code(REQUEST_VALIDATION_ERROR)
                        .message(resultValidation)
                        .httpStatus(BAD_REQUEST)
                        .build();
            }
        }
    }
}
