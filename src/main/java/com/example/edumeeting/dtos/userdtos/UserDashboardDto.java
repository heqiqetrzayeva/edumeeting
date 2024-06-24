package com.example.edumeeting.dtos.userdtos;

import com.example.edumeeting.dtos.roledtos.RoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDashboardDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean emailConfirmed;
    private List<RoleDto> roles = new ArrayList<>();
}
