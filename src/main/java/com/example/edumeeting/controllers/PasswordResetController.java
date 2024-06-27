package com.example.edumeeting.controllers;

import com.example.edumeeting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordResetController {
    @Autowired
    private UserService userService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String email, Model model) {
        userService.requestPasswordReset(email);
        model.addAttribute("message", "Şifre sıfırlama bağlantısı e-posta adresinize gönderildi.");
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPasswordForm(@RequestParam("token") String token,
                                           @RequestParam("password") String password,
                                           Model model) {
        userService.resetPassword(token, password);
        model.addAttribute("message", "Şifreniz başarıyla sıfırlandı.");
        return "reset-password";
    }
}
