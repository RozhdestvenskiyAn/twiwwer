package ru.rozhdestvenskiy.twiwwer.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Code;
import ru.rozhdestvenskiy.twiwwer.domain.constant.Error;
import ru.rozhdestvenskiy.twiwwer.domain.responce.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException  {
        log.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(ErrorResponse.builder()
                        .error(Error.builder()
                                .code(Code.AUTHORIZATION_ERROR)
                                .message(authException.getMessage())
                                .build())
                        .build()));
    }
}