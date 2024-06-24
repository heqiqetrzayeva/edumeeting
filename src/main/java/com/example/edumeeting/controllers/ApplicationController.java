package com.example.edumeeting.controllers;

import com.example.edumeeting.models.Application;
import com.example.edumeeting.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @GetMapping("/admin/submit")
    public String formCreate(Model model) {
        return "/admin/submit";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("application", new Application());
        return "/admin/form/form";
    }

//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute Application application, Model model) {
//        service.save(application);
//        model.addAttribute("message", "Application submitted successfully!");
//        return "/admin/form/submit";
//    }


//
}
