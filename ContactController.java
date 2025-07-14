package com.protechamc.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public Map<String, Object> sendEmail(@RequestBody Map<String, String> form) {
        try {
            String name = form.get("name");
            String email = form.get("email");
            String message = form.get("message");

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("your-email@gmail.com");
            mailMessage.setSubject("New Contact Form Message");
            mailMessage.setText("From: " + name + " <" + email + ">

" + message);

            mailSender.send(mailMessage);
            return Map.of("success", true);
        } catch (Exception e) {
            return Map.of("success", false, "error", e.getMessage());
        }
    }
}