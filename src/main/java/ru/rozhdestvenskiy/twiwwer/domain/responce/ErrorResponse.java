package ru.rozhdestvenskiy.twiwwer.domain.responce;

import lombok.Builder;
import lombok.Data;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Error;

@Data
@Builder
public class ErrorResponse implements Response {
    private Error error;
}
