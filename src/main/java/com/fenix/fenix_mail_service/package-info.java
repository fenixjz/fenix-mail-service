/**
 * The Fenix Mail Service is a flexible and lightweight email service designed for use in
 * Spring Boot applications. It provides functionality for sending emails, managing
 * configuration properties, and logging email activity in a structured JSON format.
 *
 * <p><b>Main Features:</b></p>
 * <ul>
 *     <li>Sending plain text and HTML emails.</li>
 *     <li>Adding optional attachments to emails.</li>
 *     <li>Logging email transactions into a JSON file for auditing and analysis.</li>
 *     <li>Configurable properties for SMTP server settings and email behavior.</li>
 * </ul>
 *
 * <p><b>Package Structure:</b></p>
 * <ul>
 *     <li>{@link com.fenix.fenix_mail_service.component}: Contains configuration properties and other essential components.</li>
 *     <li>{@link com.fenix.fenix_mail_service.configuration}: Provides Spring configuration classes, such as the {@code JavaMailSender} bean.</li>
 *     <li>{@link com.fenix.fenix_mail_service.model}: Includes data models for email requests and logs.</li>
 *     <li>{@link com.fenix.fenix_mail_service.service}: Implements core service functionality, including email sending and log management.</li>
 * </ul>
 *
 * <p><b>Integration Steps:</b></p>
 * <ol>
 *     <li>Add the Fenix Mail Service dependency to your project.</li>
 *     <li>Define the required properties in your {@code application.properties} or {@code application.yml}:</li>
 * </ol>
 *
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
 * private FenixMailService mailService;
 *
 * public void sendEmail() {
 *     FenixEmailRequest request = new FenixEmailRequest();
 *     request.setTo(List.of("recipient@example.com"));
 *     request.setSubject("Test Email");
 *     request.setContent("This is a test email.");
 *     request.setHtml(true);
 *     request.setAttachment(new File("/path/to/file.pdf"));
 *
 *     boolean isSent = mailService.sendJson(request);
 *     if (isSent) {
 *         System.out.println("Email sent successfully.");
 *     } else {
 *         System.out.println("Failed to send email.");
 *     }
 * }
 * }</pre>
 *
 * <p><b>Note:</b></p>
 * <ul>
 *     <li>This service is Spring-based and requires a properly configured {@code JavaMailSender} bean.</li>
 *     <li>Email logs are stored in a JSON file, the location of which is configurable via properties.</li>
 * </ul>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */
package com.fenix.fenix_mail_service;