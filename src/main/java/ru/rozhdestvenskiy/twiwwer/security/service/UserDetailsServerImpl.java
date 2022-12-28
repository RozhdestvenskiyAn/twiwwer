package ru.rozhdestvenskiy.twiwwer.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.twiwwer.model.User;
import ru.rozhdestvenskiy.twiwwer.repository.UserRepository;

@Slf4j
@Service
public class UserDetailsServerImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByNickname(username).orElseThrow(() ->
                {
                    log.error("User with nickname: " + username + " is not registered");
                    return new UsernameNotFoundException("User is not registered");
                });
        log.info("Got user: {} by nickname: {}", user, username);

        return new UserDetailsImpl(user);
    }
}
