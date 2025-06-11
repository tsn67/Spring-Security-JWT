package com.dependancy.springsecuritybackend.dtos;

import com.dependancy.springsecuritybackend.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {

    @NotBlank(message = "Name should not be blank")
    @JsonProperty("username")
    @Size(min = 6, max = 30, message = "Name should be between 6 and 30 characters")
    private String name;

    // can be blang
    @Email(message = "Email should be valid")
    private String email = null;

    @NotBlank
    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
    private String password;

    private Role role = Role.valueOf("USER");
}
