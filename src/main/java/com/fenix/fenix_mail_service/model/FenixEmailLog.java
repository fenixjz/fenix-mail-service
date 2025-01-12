package com.fenix.fenix_mail_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a log entry for an email sent using the Fenix Mail Service.
 * <p>
 * This class stores information about an email transaction, including recipients, subject,
 * content, timestamp, and the success status of the operation. It is primarily used for
 * logging purposes and can be serialized into a JSON file for audit and debugging.
 * </p>
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *     <li>{@code recipients}: A list of recipient email addresses.</li>
 *     <li>{@code subject}: The subject of the email.</li>
 *     <li>{@code body}: The body content of the email.</li>
 *     <li>{@code sentAt}: The timestamp when the email was sent.</li>
 *     <li>{@code success}: A flag indicating whether the email was successfully sent.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * FenixEmailLog log = new FenixEmailLog();
 * log.setRecipients(List.of("recipient@example.com"));
 * log.setSubject("Test Subject");
 * log.setBody("This is the email content.");
 * log.setSentAt(LocalDateTime.now());
 * log.setSuccess(true);
 *
 * System.out.println(log);
 * }</pre>
 *
 * <p><b>Example JSON Representation:</b></p>
 * <pre>
 * {
 *     "recipients": ["recipient1@example.com", "recipient2@example.com"],
 *     "subject": "Test Subject",
 *     "body": "This is the email content.",
 *     "sentAt": "2025-01-12T10:15:30",
 *     "success": true
 * }
 * </pre>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FenixEmailLog {
    private List<String> recipients;
    private String subject;
    private String body;
    private LocalDateTime sentAt;
    private boolean success;
}
