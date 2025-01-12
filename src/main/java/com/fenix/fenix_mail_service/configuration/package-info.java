/**
 * This package contains configuration classes for the Fenix Mail Service.
 * <p>
 * The primary purpose of this package is to provide Spring-managed beans and
 * configuration settings required for the proper operation of the mail service.
 * </p>
 *
 * <p><b>Key Class:</b></p>
 * <ul>
 *     <li>{@link com.fenix.fenix_mail_service.configuration.FenixMailConfig}</li>
 * </ul>
 *
 * <p><b>FenixMailConfig:</b></p>
 * This class is responsible for configuring the {@link org.springframework.mail.javamail.JavaMailSender}
 * bean, which is used to send emails. The configuration relies on the properties defined in the
 * {@link com.fenix.fenix_mail_service.component.FenixMailProperties} class.
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Configures the SMTP server details, including host, port, username, and password.</li>
 *     <li>Enables optional features like STARTTLS and authentication.</li>
 *     <li>Sets default email properties such as encoding and sender address.</li>
 * </ul>
 *
 * <p><b>Integration:</b></p>
 * To use this configuration, ensure the following steps:
 * <ol>
 *     <li>Add the Fenix Mail Service dependency to your project.</li>
 *     <li>Define the necessary properties in your {@code application.properties} or {@code application.yml} file:</li>
 * </ol>
 *
 * <pre>{@code
 * fenix.spring.mail.host=smtp.example.com
 * fenix.spring.mail.port=587
 * fenix.spring.mail.username=your-username
 * fenix.spring.mail.password=your-password
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
 */
package com.fenix.fenix_mail_service.configuration;