package com.example.edumeeting.services;//package com.example.demo.services;

public interface EmailService {

    void sendConfirmationEmail(String email, String token);
}
