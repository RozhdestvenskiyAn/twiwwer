package ru.rozhdestvenskiy.twiwwer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.twiwwer.domain.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.domain.responce.Response;
import ru.rozhdestvenskiy.twiwwer.domain.responce.exception.CommonException;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;
import ru.rozhdestvenskiy.twiwwer.mapper.UserMapper;
import ru.rozhdestvenskiy.twiwwer.model.User;
import ru.rozhdestvenskiy.twiwwer.repository.UserRepository;
import ru.rozhdestvenskiy.twiwwer.util.ValidationUtils;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.EMAIL_BUSY;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.NICKNAME_BUSY;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwiwwerServiceImpl implements TwiwwerService {

    private final ValidationUtils validationUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;


    @Transactional
    public ResponseEntity<Response> registration(RegistrationReq registrationReq) {

        log.info("Validation registration request: {}", registrationReq);
        validationUtils.validationRequest(registrationReq);

        log.info("Map registration request: {}", registrationReq);
        UserDto userDto = userMapper.registrationReqMapToUserDto(registrationReq);

        log.info("Check nickname: {} and email: {} for uniqueness",
                userDto.getNickname(), userDto.getEmail());
        checkNicknameAndEmail(userDto);

        log.info("Map userDto: {}", registrationReq);
        User user = userMapper.userDtoMapToUser(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setTimeInsert(LocalDateTime.now());

        log.info("Save user: {}", user);
        userRepository.save(user);

        // TODO: 25.12.2022 return
        return null;
    }

    private void checkNicknameAndEmail(UserDto registrationUserDto) {

        if (userRepository.existsByNickname(registrationUserDto.getNickname())) {
            log.error("Nick name is already token: {}", registrationUserDto.getNickname());
            throw CommonException.builder()
                    .code(NICKNAME_BUSY)
                    .message("Nick name is already token")
                    .httpStatus(BAD_REQUEST)
                    .build();
        }

        if (userRepository.existsByEmail(registrationUserDto.getEmail())) {

            log.error("Email is already token: {}", registrationUserDto.getEmail());
            throw CommonException.builder()
                    .code(EMAIL_BUSY)
                    .message("Email is already token")
                    .httpStatus(BAD_REQUEST)
                    .build();
        }
    }
}
