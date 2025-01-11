package com.fenix.fenix_mail_service.component;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fenix.spring.mail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailProperties {

    private String host = "localhost";
    private int port = 25;
    private String username = "";
    private String password = "";
    private String defaultEncoding = "UTF-8";
    private boolean auth = false;
    private boolean starttlsEnable = false;
    private String fromAddress = "";
    private String logPath = "";
}
