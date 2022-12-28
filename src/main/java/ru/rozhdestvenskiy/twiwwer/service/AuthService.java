package ru.rozhdestvenskiy.twiwwer.service;

import ru.rozhdestvenskiy.twiwwer.dto.UserDto;

public interface AuthService {
    UserDto registration (UserDto UserDto);

    UserDto login(UserDto userDto);
}
