package com.example.edumeeting.dtos.contactdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactCreateDto {
    private String name;
    private String email;
    private String message;
    private String resume;

}
