package com.example.edumeeting.dtos.applicationdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicationDto {
    private Long id;

    private String name;

    private String email;

    private String message;
}
