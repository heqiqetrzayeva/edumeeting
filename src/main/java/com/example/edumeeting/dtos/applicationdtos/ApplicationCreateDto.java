package com.example.edumeeting.dtos.applicationdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicationCreateDto {

    private String name;

    private String email;

    private String message;
}
