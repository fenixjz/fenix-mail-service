package com.fenix.fenix_mail_service.configuration;

import com.fenix.fenix_mail_service.component.MailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
@RequiredArgsConstructor
public class MailSenderConfig {

    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        System.out.println("Mail Host: " + mailProperties.getHost());
        System.out.println("Mail Port: " + mailProperties.getPort());
        System.out.println("Mail Username: " + mailProperties.getUsername());
        System.out.println("Mail Password: " + mailProperties.getPassword());
        System.out.println("Mail Sent From: " + mailProperties.getFromAddress());
        System.out.println("mail.default-encoding: " + mailProperties.getDefaultEncoding());
        System.out.println("mail.smtp.auth: " + mailProperties.isAuth());
        System.out.println("mail.smtp.starttls.enable: " + mailProperties.isStarttlsEnable());

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setDefaultEncoding(mailProperties.getDefaultEncoding());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.host", mailProperties.getHost());
        props.put("mail.port", mailProperties.getPort());
        props.put("mail.username", mailProperties.getUsername());
        props.put("mail.password", mailProperties.getPassword());
        props.put("mail.from-address", mailProperties.getFromAddress());
        props.put("mail.default-encoding", mailProperties.getDefaultEncoding());
        props.put("mail.smtp.auth", mailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.isStarttlsEnable());
        props.put("mail.log-path", mailProperties.getLogPath());

        return mailSender;
    }
}
