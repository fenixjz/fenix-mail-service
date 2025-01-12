package com.fenix.fenix_mail_service.configuration;

import com.fenix.fenix_mail_service.component.FenixMailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration class for the Fenix Mail Service.
 * <p>
 * This class provides the necessary configuration for setting up a {@link JavaMailSender} bean,
 * which is used for sending emails in the application. The configuration relies on the
 * properties defined in the {@link FenixMailProperties} class.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Reads email-related configuration properties from the application configuration file.</li>
 *     <li>Configures a {@link JavaMailSender} bean to handle email-sending functionality.</li>
 *     <li>Supports additional SMTP settings such as authentication and STARTTLS.</li>
 * </ul>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *     <li>{@link FenixMailProperties}: Contains the configuration properties for email sending.</li>
 *     <li>{@link JavaMailSender}: The primary interface for sending emails in Spring applications.</li>
 * </ul>
 *
 * <p><b>Integration:</b></p>
 * <p>
 * The configured {@link JavaMailSender} bean is made available as a Spring-managed bean,
 * allowing it to be injected into other components or services using {@code @Autowired}.
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
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * @Autowired
 * private JavaMailSender mailSender;
 *
 * public void sendTestEmail() {
 *     MimeMessage message = mailSender.createMimeMessage();
 *     MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
 *     helper.setTo("recipient@example.com");
 *     helper.setSubject("Test Email");
 *     helper.setText("<p>This is a test email.</p>", true);
 *     mailSender.send(message);
 * }
 * }</pre>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */

@Configuration
@EnableConfigurationProperties(FenixMailProperties.class)
@RequiredArgsConstructor
public class FenixMailConfig {

    private final FenixMailProperties fenixMailProperties;

    /**
     * Configures and provides a JavaMailSender bean for the application.
     * <p>
     * This method creates and configures an instance of {@link JavaMailSenderImpl} using
     * the properties defined in the {@link FenixMailProperties} component. The configuration
     * ensures that the mail sender has all necessary SMTP settings for sending emails.
     * </p>
     *
     * <p>
     * The method performs the following steps:
     * <ul>
     *   <li>Instantiates a {@link JavaMailSenderImpl} object.</li>
     *   <li>Sets the SMTP server host, port, username, and password based on the properties
     *       defined in {@link FenixMailProperties}.</li>
     *   <li>Sets the default encoding for email messages.</li>
     *   <li>Retrieves the internal {@link Properties} object from {@link JavaMailSenderImpl}
     *       and populates additional mail properties such as authentication, STARTTLS, and
     *       logging configurations.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Key properties configured:
     * <ul>
     *   <li><b>mail.host:</b> Specifies the SMTP server host.</li>
     *   <li><b>mail.port:</b> Specifies the SMTP server port.</li>
     *   <li><b>mail.username:</b> Sets the username for SMTP authentication.</li>
     *   <li><b>mail.password:</b> Sets the password for SMTP authentication.</li>
     *   <li><b>mail.from-address:</b> Specifies the default sender's email address.</li>
     *   <li><b>mail.default-encoding:</b> Specifies the default encoding for email content.</li>
     *   <li><b>mail.smtp.auth:</b> Enables or disables SMTP authentication.</li>
     *   <li><b>mail.smtp.starttls.enable:</b> Enables or disables STARTTLS support for secure connections.</li>
     *   <li><b>mail.log-path:</b> Configures the path for logging email transactions.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The configured {@link JavaMailSender} instance is managed as a Spring bean, allowing it to
     * be injected into other components or services for sending emails.
     * </p>
     *
     * @return A fully configured {@link JavaMailSender} instance ready for sending emails.
     */
    @Bean
    public JavaMailSender javaMailSender() {
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
