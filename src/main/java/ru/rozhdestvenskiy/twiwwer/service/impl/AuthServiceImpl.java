package ru.rozhdestvenskiy.twiwwer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.twiwwer.exception.CommonException;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;
import ru.rozhdestvenskiy.twiwwer.mapper.UserMapper;
import ru.rozhdestvenskiy.twiwwer.model.User;
import ru.rozhdestvenskiy.twiwwer.repository.UserRepository;
import ru.rozhdestvenskiy.twiwwer.security.service.UserDetailsImpl;
import ru.rozhdestvenskiy.twiwwer.service.AuthService;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.EMAIL_BUSY;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.NICKNAME_BUSY;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserDto registration(UserDto registrationUserDto) {

        log.info("Check nickname: {} and email: {} for uniqueness",
                registrationUserDto.getNickname(), registrationUserDto.getEmail());
        checkNicknameAndEmail(registrationUserDto);

        log.info("Map userDto: {}", registrationUserDto);
        User user = userMapper.userDtoMapToUser(registrationUserDto);
        user.setPassword(encoder.encode(registrationUserDto.getPassword()));
        user.setTimeInsert(LocalDateTime.now());

        log.info("Save user: {}", user);
        User savedUser = userRepository.save(user);

        return userMapper.userMapToUserDto(savedUser);
    }

    @Transactional
    public UserDto login(UserDto loginUserDto) {

        log.info("Authentication  user: {}", loginUserDto);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getNickname(), loginUserDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userMapper.userMapToUserDto(userDetails.getUser());

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
