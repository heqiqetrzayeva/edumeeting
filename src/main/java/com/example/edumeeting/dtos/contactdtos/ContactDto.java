package com.example.edumeeting.dtos.contactdtos;

import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ContactDto {
    private Long id;
    private String name;
    private String email;
    private String message;
    private MultipartFile resume;
    private String resumePath;
    private VacancyDto vacancy;

}
