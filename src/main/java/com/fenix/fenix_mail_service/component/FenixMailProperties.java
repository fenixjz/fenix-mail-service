package com.fenix.fenix_mail_service.component;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for the Fenix Mail Service.
 * <p>
 * This class defines all configurable properties required for the email service, such as
 * SMTP server details, authentication settings, and logging configurations. The properties
 * are mapped from the application configuration file (e.g., {@code application.properties}
 * or {@code application.yml}) using the prefix {@code fenix.spring.mail}.
 * </p>
 *
 * <p><b>Key Properties:</b></p>
 * <ul>
 *     <li>{@code host}: The SMTP server host (default is "localhost").</li>
 *     <li>{@code port}: The SMTP server port (default is 25).</li>
 *     <li>{@code username}: The username for SMTP authentication.</li>
 *     <li>{@code password}: The password for SMTP authentication.</li>
 *     <li>{@code defaultEncoding}: The default character encoding for emails (default is UTF-8).</li>
 *     <li>{@code auth}: A flag indicating whether SMTP authentication is enabled.</li>
 *     <li>{@code starttlsEnable}: A flag indicating whether STARTTLS is enabled for secure connections.</li>
 *     <li>{@code fromAddress}: The default sender email address.</li>
 *     <li>{@code logPath}: The file path where email logs are stored.</li>
 * </ul>
 *
 * <p><b>Integration:</b></p>
 * <p>
 * This class is annotated with {@code @ConfigurationProperties}, allowing it to automatically
 * map properties from the configuration file. It is also marked as a Spring {@code @Component},
 * so it is available as a managed bean.
 * </p>
 *
 * <p><b>Example Configuration:</b></p>
 * <pre>{@code
 * fenix.spring.mail.host=smtp.example.com
 * fenix.spring.mail.port=587
 * fenix.spring.mail.username=my-username
 * fenix.spring.mail.password=my-password
 * fenix.spring.mail.default-encoding=UTF-8
 * fenix.spring.mail.auth=true
 * fenix.spring.mail.starttls.enable=true
 * fenix.spring.mail.from-address=no-reply@example.com
 * fenix.spring.mail.log-path=/var/logs/mail_log.json
 * }</pre>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */

@Component
@ConfigurationProperties(prefix = "fenix.spring.mail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FenixMailProperties {
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
