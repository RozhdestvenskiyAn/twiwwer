package ru.rozhdestvenskiy.twiwwer.contrroler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rozhdestvenskiy.twiwwer.domain.api.auth.LoginReq;
import ru.rozhdestvenskiy.twiwwer.domain.api.auth.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.api.auth.AuthRes;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.domain.responce.SuccessResponse;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;
import ru.rozhdestvenskiy.twiwwer.mapper.UserMapper;
import ru.rozhdestvenskiy.twiwwer.security.jwt.JwtUtils;
import ru.rozhdestvenskiy.twiwwer.service.AuthService;
import ru.rozhdestvenskiy.twiwwer.util.ValidationUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final ValidationUtils validationUtils;
    private final AuthService authService;
    private final UserMapper userMapper;

    private final JwtUtils jwtUtils;


    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@RequestBody RegistrationReq registrationReq) {
        log.info("START endpoint registration, request: {}", registrationReq);

        log.info("Validation registration request: {}", registrationReq);
        validationUtils.validationRequest(registrationReq);

        log.info("Mapping registration request: {}", registrationReq);
        UserDto userDto = userMapper.registrationReqMapToUserDto(registrationReq);

        UserDto registrationUserDto = authService.registration(userDto);

        ResponseEntity<Response> response = new ResponseEntity<>( SuccessResponse.builder()
                .data(AuthRes.builder()
                        .message("Registration " + registrationUserDto.getNickname() + " was successful")
                        .build())
                .build(), HttpStatus.OK);
        log.info("END endpoint registration, response: {}", response);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> authenticateUser(@RequestBody LoginReq loginReq) {
        log.info("START endpoint login, request: {}", loginReq);

        log.info("Validation login request: {}", loginReq);
        validationUtils.validationRequest(loginReq);

        log.info("Mapping login request: {}", loginReq);
        UserDto userDto = userMapper.loginReqMapToUserDto(loginReq);

        UserDto authenticatedUserDto = authService.login(userDto);

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authenticatedUserDto);

        ResponseEntity<Response> response = ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(SuccessResponse.builder()
                        .data(AuthRes.builder()
                                .message("User: " + authenticatedUserDto.getNickname() + " authentication was successful")
                                .build())
                        .build());
        log.info("END endpoint login, response: {}", response);
        return response;
    }
}
