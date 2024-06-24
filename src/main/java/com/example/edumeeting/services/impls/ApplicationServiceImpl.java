package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.applicationdtos.ApplicationDto;
import com.example.edumeeting.models.Application;
import com.example.edumeeting.repositories.ApplicationRepository;
import com.example.edumeeting.services.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository repository;
    @Autowired
    private ModelMapper modelMapper;
//
//    public Application save(Application application) {
//        return repository.save(application);
//    }

//    @Override
//    public void paginateItems() {
//        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
//        Page<Application> allProducts = repository.findAll(firstPageWithTwoElements);
//
//
//        Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
//        List<Application> allTenDollarProducts =
//                repository.findAllByName("", secondPageWithFiveElements);
//
//    }

    @Override
    public Page<ApplicationDto> getApplications(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applications = repository.findAll(pageable);
//        List<Application> listOfApplications = applications.getContent();
       return applications.map(application ->
               modelMapper.map(application, ApplicationDto.class));
    }

    @Override
    public List<ApplicationDto> getApplicationsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applications = repository.findByName(name, pageable);


        List<ApplicationDto> applicationDtos = applications.getContent().stream()
                .map(application -> modelMapper.map(application, ApplicationDto.class))
                .collect(Collectors.toList());
        return applicationDtos;
    }

}
