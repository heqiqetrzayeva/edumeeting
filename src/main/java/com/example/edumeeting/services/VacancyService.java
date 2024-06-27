package com.example.edumeeting.services;

import com.example.edumeeting.dtos.vacancydtos.VacancyCreateDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;

import java.util.List;

public interface VacancyService{
     List<VacancyDto> getAllVacancies();
     void addVacancy(VacancyCreateDto vacancyCreateDto);
//     VacancyDto getVacancyById(Long id);
     List<VacancyDto> searchVacancies(String keyword);
     VacancyDto findVacancy(Long id);
}
