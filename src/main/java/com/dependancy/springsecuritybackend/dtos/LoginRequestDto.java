package com.dependancy.springsecuritybackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank(message = "Username should not be blank")
    @JsonProperty("username")
    private String name;

    @NotBlank(message = "Password should not be blank")
    private String password;
}
