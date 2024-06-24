package com.example.edumeeting.services;


import com.example.edumeeting.dtos.authdtos.RegisterDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDto;

import java.util.List;

public interface UserService {

    boolean register(RegisterDto registerDto);
    List<UserDto> getAllRegisters();

    void addUser(UserCreateDto userCreateDto);
    List<UserDto> getDashboardUsers();
    UserDto getUserById(Long id);

    boolean confirmEmail(String email, String token);

}
