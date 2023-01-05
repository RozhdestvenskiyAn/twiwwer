package ru.rozhdestvenskiy.twiwwer.domain.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class Error {
    private Code code;
    private String message;
}
