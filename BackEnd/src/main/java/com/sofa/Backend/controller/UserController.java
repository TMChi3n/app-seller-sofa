package com.sofa.Backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofa.Backend.dto.UserDto;
import com.sofa.Backend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody UserDto userDto) {

        if (userService.emailExists(userDto.getEmail())) {
            return new ResponseEntity<>("Email is already registered!!!", HttpStatus.BAD_REQUEST);
        }

        UserDto register = userService.register(userDto);

        if (register != null) {
            return new ResponseEntity<>(register, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        if (username == null || password == null) {
            return new ResponseEntity<>("Username or password is missing", HttpStatus.BAD_REQUEST);
        }

        UserDto loggedInUser = userService.login(username, password);
        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}