package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import com.example.edumeeting.services.CategoryService;
import com.example.edumeeting.services.ContactService;
import com.example.edumeeting.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ContactController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @Autowired
    private ContactService contactService;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/contact")
    public String getContact(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<ContactDto> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies",vacancies);
        return "contact";
    }

    @PostMapping("/contact")
    public String createContact(@ModelAttribute ContactCreateDto contactCreateDto ) throws IOException {
        UUID rand = UUID.randomUUID();
        MultipartFile resume = contactCreateDto.getResume();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + resume.getOriginalFilename());
        fileNames.append(resume.getOriginalFilename());
        Files.write(fileNameAndPath, resume.getBytes());

        contactCreateDto.setResumePath(rand + resume.getOriginalFilename());
        contactService.addContact(contactCreateDto);
        return "redirect:contact";
    }
}
