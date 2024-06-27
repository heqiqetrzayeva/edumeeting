package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.roledtos.RoleDto;
import com.example.edumeeting.dtos.userdtos.UserAddRoleDto;
import com.example.edumeeting.dtos.userdtos.UserDashboardDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.services.RoleService;
import com.example.edumeeting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/admin/users")
    public String getUsers(Model model){

        List<UserDashboardDto> userList = userService.getDashboardUsers();
        model.addAttribute("users", userList);
        return "/admin/auth/user-list";
    }

    @GetMapping("/admin/users/role/{id}")
    public String addRole(@PathVariable Long id, Model model){

        List<RoleDto> roles = roleService.getRoles();
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/admin/auth/user-role";
    }

    @PostMapping("/admin/users/role")
    public String addRole(@ModelAttribute UserAddRoleDto userAddRoleDto){

        userService.addRole(userAddRoleDto);
        return "redirect:/admin/users";
    }

}
