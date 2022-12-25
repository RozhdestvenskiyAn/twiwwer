package ru.rozhdestvenskiy.twiwwer.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rozhdestvenskiy.twiwwer.domain.responce.exception.CommonException;
import ru.rozhdestvenskiy.twiwwer.model.User;
import ru.rozhdestvenskiy.twiwwer.repository.UserRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static ru.rozhdestvenskiy.twiwwer.domain.constant.Code.USER_NOT_FOUND;

@Slf4j
@Service
public class UserDetailsServerImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("11");
        User user = userRepository.findByNickname(username).orElseThrow(() -> CommonException.builder()
                .code(USER_NOT_FOUND)
                .message("User Not Found with username: " + username)
                .httpStatus(NOT_FOUND)
                .build());
        log.info("11");

        return new UserDetailsImpl(user);
    }
}
