/**
 * This package contains component-level classes for configuring and managing
 * the Fenix Mail Service.
 *
 * <p>
 * The primary purpose of this package is to provide configuration properties
 * and components required for the proper functioning of the email service.
 * </p>
 *
 * <p><b>Key Class:</b></p>
 * <ul>
 *     <li>{@link com.fenix.fenix_mail_service.component.FenixMailProperties}</li>
 * </ul>
 *
 * <p><b>FenixMailProperties:</b></p>
 * This class holds all configurable properties for the mail service, such as:
 * <ul>
 *     <li><b>SMTP server details:</b> host, port, username, and password.</li>
 *     <li><b>Authentication settings:</b> Whether SMTP authentication and STARTTLS are enabled.</li>
 *     <li><b>Default settings:</b> Encoding and sender email address.</li>
 *     <li><b>Logging:</b> Path to the file where email logs are stored.</li>
 * </ul>
 *
 * <p><b>Integration:</b></p>
 * <p>
 * The {@link FenixMailProperties} class is annotated with {@code @ConfigurationProperties},
 * allowing it to map properties from the application's configuration file (e.g., 
 * {@code application.properties} or {@code application.yml}) using the prefix 
 * {@code fenix.spring.mail}.
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
 */
package com.fenix.fenix_mail_service.component;
