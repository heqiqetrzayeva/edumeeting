package com.example.edumeeting.services;


import com.example.edumeeting.dtos.authdtos.RegisterDto;
import com.example.edumeeting.dtos.userdtos.UserAddRoleDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDashboardDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.models.UserEntity;

import java.util.List;

public interface UserService {

    boolean register(RegisterDto registerDto);

    UserEntity findByEmail(String email);
//    List<UserDto> getAllRegisters();

    void addUser(UserCreateDto userCreateDto);
    List<UserDashboardDto> getDashboardUsers();
    UserDto getUserById(Long id);

    boolean confirmEmail(String email, String token);

    void addRole(UserAddRoleDto userAddRoleDto);

    void resetPassword(String token, String password);

    void requestPasswordReset(String email);




}
