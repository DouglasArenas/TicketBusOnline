package com.um.main.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final Dotenv dotenv;

    public EmailService(JavaMailSender emailSender, Dotenv dotenv) {
        this.emailSender = emailSender;
        this.dotenv = dotenv;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dotenv.get("SPRING_MAIL_USERNAME"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}