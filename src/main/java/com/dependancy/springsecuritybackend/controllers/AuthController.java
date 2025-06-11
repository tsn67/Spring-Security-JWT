package com.dependancy.springsecuritybackend.controllers;

import com.dependancy.springsecuritybackend.dtos.LoginRequestDto;
import com.dependancy.springsecuritybackend.dtos.UserRegisterDto;
import com.dependancy.springsecuritybackend.exceptions.UserAlreadyExistsException;
import com.dependancy.springsecuritybackend.services.CustomUserDetailsService;
import com.dependancy.springsecuritybackend.services.JwtUtil;
import com.dependancy.springsecuritybackend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto request) {
        var registerResponse = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(), request.getPassword()
                )
        );


        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getName());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(Map.of("token", jwtToken));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<?> handleUserConflict(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "User already exists"));
    }
}
