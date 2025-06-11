package com.dependancy.springsecuritybackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegisterResponseDto {

    private Long id;

    @JsonProperty("username")
    private String name;
}
