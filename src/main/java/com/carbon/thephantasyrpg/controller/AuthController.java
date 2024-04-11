package com.carbon.thephantasyrpg.controller;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO registrationDto) {
        User registeredUser = userService.registerUser(registrationDto);
        return ResponseEntity.ok("User registered successfully with username: " + registeredUser.getUsername());
    }

}
