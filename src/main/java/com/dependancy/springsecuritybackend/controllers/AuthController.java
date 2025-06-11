package com.dependancy.springsecuritybackend.controllers;

import com.dependancy.springsecuritybackend.dtos.UserRegisterDto;
import com.dependancy.springsecuritybackend.exceptions.UserAlreadyExistsException;
import com.dependancy.springsecuritybackend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto request) {
        var registerResponse = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<?> handleUserConflict(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "User already exists"));
    }
}
