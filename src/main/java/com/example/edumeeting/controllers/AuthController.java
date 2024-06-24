package com.example.edumeeting.controllers;//package com.example.demo.controllers;
//
//
//import com.example.demo.dtos.authdtos.RegisterDto;
//import com.example.demo.dtos.userdtos.UserDto;
//import com.example.demo.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//@Controller
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/login")
//    public String login()
//    {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String register()
//    {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String register(RegisterDto registerDto)
//    {
//        boolean result = userService.register(registerDto);
//        if(result==false)
//        {
//            return "register";
//        }
//        return "redirect:login";
//    }
//
////    @GetMapping("/auth/confirm")
////    public String confirm(String email, String token){
////        userService.confirmEmail(email, token);
////        return "redirect:login";
////    }
//}
