package com.dependancy.springsecuritybackend.mappers;

import com.dependancy.springsecuritybackend.dtos.UserRegisterDto;
import com.dependancy.springsecuritybackend.dtos.UserRegisterResponseDto;
import com.dependancy.springsecuritybackend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRegisterDto userRegisterDto);
    UserRegisterResponseDto toUserRegisterResponseDto(User user);
}
