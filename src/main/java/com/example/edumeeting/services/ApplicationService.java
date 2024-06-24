package com.example.edumeeting.services;

import com.example.edumeeting.dtos.applicationdtos.ApplicationDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplicationService{

    Page<ApplicationDto> getApplications(int page, int size);

    List<ApplicationDto> getApplicationsByName(String name, int page, int size);

}
