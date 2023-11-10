package com.um.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

@Configuration
public class MailConfig {

    Dotenv dotenv = Dotenv.load();

    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }
    
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(dotenv.get("SPRING_MAIL_HOST"));
        mailSender.setPort(Integer.parseInt(dotenv.get("SPRING_MAIL_PORT")));

        mailSender.setUsername(dotenv.get("SPRING_MAIL_USERNAME"));
        mailSender.setPassword(dotenv.get("SPRING_MAIL_PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}