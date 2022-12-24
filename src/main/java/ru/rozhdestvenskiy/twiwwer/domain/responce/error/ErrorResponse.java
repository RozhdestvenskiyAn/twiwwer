package ru.rozhdestvenskiy.twiwwer.domain.responce.error;

import lombok.Builder;
import lombok.Data;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;

@Data
@Builder
public class ErrorResponse implements Response {
    private Error error;
}
