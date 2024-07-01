package com.example.edumeeting.controllers;


import com.example.edumeeting.dtos.roledtos.RoleDto;
import com.example.edumeeting.dtos.userdtos.UserAddRoleDto;
import com.example.edumeeting.dtos.userdtos.UserDashboardDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String index() {
        return "/admin/index";
    }

    @GetMapping("/admin/comments")
    public String comments(Model model) {
        return "/admin/comments";
    }


}
