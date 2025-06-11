package com.dependancy.springsecuritybackend.services;

import com.dependancy.springsecuritybackend.dtos.UserRegisterDto;
import com.dependancy.springsecuritybackend.dtos.UserRegisterResponseDto;
import com.dependancy.springsecuritybackend.exceptions.UserAlreadyExistsException;
import com.dependancy.springsecuritybackend.mappers.UserMapper;
import com.dependancy.springsecuritybackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRegisterResponseDto registerUser(UserRegisterDto request) {
        var existingUser = userRepository.findByName(request.getName()).orElse(null);
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        var user = userMapper.toUser(request);
        userRepository.save(user);
        return userMapper.toUserRegisterResponseDto(user);
    }

}
