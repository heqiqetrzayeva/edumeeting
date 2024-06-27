package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyCreateDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import com.example.edumeeting.models.Article;
import com.example.edumeeting.models.Category;
import com.example.edumeeting.models.Contact;
import com.example.edumeeting.models.Vacancy;
import com.example.edumeeting.repositories.ContactRepository;
import com.example.edumeeting.repositories.VacancyRepository;
import com.example.edumeeting.services.VacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<VacancyDto> vacancies = vacancyRepository.findAll().stream()
                .map(Vacancy->modelMapper.map(Vacancy, VacancyDto.class))
                .collect(Collectors.toList());
        return vacancies;
    }


    @Override
    public void addVacancy(VacancyCreateDto vacancyCreateDto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancyCreateDto.getName());
        vacancy.setDescription(vacancyCreateDto.getDescription());
        vacancyRepository.save(vacancy);
    }

//    @Override
//    public VacancyDto getVacancyById(Long id) {
//        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
//        VacancyDto vacanciesCreateDto = modelMapper.map(vacancy, VacancyDto.class);
//        return vacanciesCreateDto;
//    }

    @Override
    public List<VacancyDto> searchVacancies(String keyword) {
        List<Vacancy> vacancies = vacancyRepository.findByName(keyword.toLowerCase());
        List<VacancyDto> vacancyDtos = vacancies.stream().
                map(Vacancy->modelMapper.map(Vacancy, VacancyDto.class))
                .collect(Collectors.toList());
        return vacancyDtos;
    }

    @Override
    public VacancyDto findVacancy(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        VacancyDto vacancyDto = modelMapper.map(vacancy, VacancyDto.class);
        return vacancyDto;
    }
}
