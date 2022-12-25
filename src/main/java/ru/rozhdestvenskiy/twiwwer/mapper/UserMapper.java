package ru.rozhdestvenskiy.twiwwer.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.twiwwer.domain.RegistrationReq;
import ru.rozhdestvenskiy.twiwwer.dto.UserDto;
import ru.rozhdestvenskiy.twiwwer.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto registrationReqMapToUserDto (RegistrationReq registrationReq);

    User userDtoMapToUser (UserDto userDto);
}
