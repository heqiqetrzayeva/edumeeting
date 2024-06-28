package com.example.edumeeting.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class TestController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/test")
    @ResponseBody
    public String test(Principal principal) {
        return "Authenticated user: " + principal.getName();
    }
}
