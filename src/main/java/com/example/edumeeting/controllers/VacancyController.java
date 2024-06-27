package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyCreateDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import com.example.edumeeting.services.ContactService;
import com.example.edumeeting.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private ContactService contactService;


    @GetMapping("/vacancy")
    public String vacancy(Model model) {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies",vacancies);
        List<ContactDto> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "vacancy";
    }

    @GetMapping("/admin/vacancy/create")
    public String vacancyCreate() {
        return "/admin/vacancy/create";
    }

    @PostMapping("/admin/vacancy/create")
    public String vacancyCreate(@ModelAttribute VacancyCreateDto vacancyCreateDto)
    {
        vacancyService.addVacancy(vacancyCreateDto);
        return "redirect:/admin/resumes";
    }

    @PostMapping("/vacancy")
    public String vacancy(String keyword, Model model) {
        List<VacancyDto> vacancies = vacancyService.searchVacancies(keyword);
        model.addAttribute("vacancies",vacancies);
        return "vacancy";
    }

    @GetMapping("/admin/resumes")
    public String resume(Model model) {
        List<ContactDto> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);

        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies",vacancies);
        return "/admin/vacancy/resumes";
    }
}
