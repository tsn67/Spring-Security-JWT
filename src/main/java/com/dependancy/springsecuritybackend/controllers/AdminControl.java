package com.dependancy.springsecuritybackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControl {

    @RequestMapping("/greet")
    public String greet() {
        return "Hello Admin";
    }

}
