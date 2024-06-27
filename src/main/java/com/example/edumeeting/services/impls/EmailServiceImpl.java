package com.example.edumeeting.services.impls;//package com.example.demo.services.impls;

import com.example.edumeeting.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendConfirmationEmail(String email, String token) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("lurline31@ethereal.email");
        mailMessage.setTo("lurline31@ethereal.email");
        mailMessage.setSubject("Confirm email");
        String result = "http://localhost:8082/auth/confirm?email=" +email+"$token="+token;
        mailMessage.setText("Şifrenizi sıfırlamak için aşağıdaki bağlantıya tıklayın:\n" + result);
        javaMailSender.send(mailMessage);
    }
}
