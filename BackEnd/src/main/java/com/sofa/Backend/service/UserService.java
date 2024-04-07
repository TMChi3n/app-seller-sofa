package com.sofa.Backend.service;

import org.springframework.stereotype.Service;

import com.sofa.Backend.dto.UserDto;

@Service
public interface UserService {

    UserDto register(UserDto userDto);

    UserDto login(String email, String password);

    boolean emailExists(String email);
    

}
