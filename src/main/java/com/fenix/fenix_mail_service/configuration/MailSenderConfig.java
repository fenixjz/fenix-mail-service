package com.fenix.fenix_mail_service.configuration;

import com.fenix.fenix_mail_service.component.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailSenderConfig {

    private final MailProperties mailProperties;

    public MailSenderConfig(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setDefaultEncoding(mailProperties.getDefaultEncoding());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.send.from", mailProperties.getSentFrom());
        props.put("mail.smtp.auth", mailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.isStarttlsEnable());

        return mailSender;
    }
}
