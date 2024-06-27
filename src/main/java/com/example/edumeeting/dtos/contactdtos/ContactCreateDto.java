package com.example.edumeeting.dtos.contactdtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ContactCreateDto {
    private Long id;
    private String name;
    private String email;
    private String message;
    private MultipartFile resume;
    private String resumePath;
    private Long vacancyId;


}
