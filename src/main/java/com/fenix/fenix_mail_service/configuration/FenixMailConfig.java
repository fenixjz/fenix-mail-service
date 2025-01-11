package com.fenix.fenix_mail_service.configuration;

import com.fenix.fenix_mail_service.component.FenixMailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(FenixMailProperties.class)
@RequiredArgsConstructor
public class FenixMailConfig {

    private final FenixMailProperties fenixMailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        System.out.println("Mail Host: " + fenixMailProperties.getHost());
        System.out.println("Mail Port: " + fenixMailProperties.getPort());
        System.out.println("Mail Username: " + fenixMailProperties.getUsername());
        System.out.println("Mail Password: " + fenixMailProperties.getPassword());
        System.out.println("Mail Sent From: " + fenixMailProperties.getFromAddress());
        System.out.println("mail.default-encoding: " + fenixMailProperties.getDefaultEncoding());
        System.out.println("mail.smtp.auth: " + fenixMailProperties.isAuth());
        System.out.println("mail.smtp.starttls.enable: " + fenixMailProperties.isStarttlsEnable());
        System.out.println("mail.log-path: " + fenixMailProperties.getLogPath());

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(fenixMailProperties.getHost());
        mailSender.setPort(fenixMailProperties.getPort());
        mailSender.setUsername(fenixMailProperties.getUsername());
        mailSender.setPassword(fenixMailProperties.getPassword());
        mailSender.setDefaultEncoding(fenixMailProperties.getDefaultEncoding());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.host", fenixMailProperties.getHost());
        props.put("mail.port", fenixMailProperties.getPort());
        props.put("mail.username", fenixMailProperties.getUsername());
        props.put("mail.password", fenixMailProperties.getPassword());
        props.put("mail.from-address", fenixMailProperties.getFromAddress());
        props.put("mail.default-encoding", fenixMailProperties.getDefaultEncoding());
        props.put("mail.smtp.auth", fenixMailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", fenixMailProperties.isStarttlsEnable());
        props.put("mail.log-path", fenixMailProperties.getLogPath());

        return mailSender;
    }
}
