package com.example.edumeeting.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;
}
