package ru.rozhdestvenskiy.twiwwer.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.twiwwer.domain.api.auth.LoginReq;
import ru.rozhdestvenskiy.twiwwer.domain.api.auth.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;
import ru.rozhdestvenskiy.twiwwer.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto registrationReqMapToUserDto (RegistrationReq registrationReq);

    User userDtoMapToUser (UserDto userDto);

    UserDto userMapToUserDto(User user);

    UserDto loginReqMapToUserDto(LoginReq loginReq);
}
