package com.fenix.fenix_mail_service.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.List;

/**
 * Represents an email request to be sent using the Fenix Mail Service.
 * <p>
 * This class is a Data Transfer Object (DTO) used for encapsulating the details of
 * an email, including recipients, subject, content, formatting, and optional attachments.
 * It ensures that required fields are validated before the email is sent.
 * </p>
 *
 * <p><b>Fields:</b></p>
 * <ul>
 *     <li>{@code to}: A list of recipient email addresses (required, must not be empty).</li>
 *     <li>{@code subject}: The subject of the email (required, must not be null).</li>
 *     <li>{@code content}: The body content of the email (required, must not be null).</li>
 *     <li>{@code isHtml}: A flag indicating whether the email content is HTML or plain text (default is false).</li>
 *     <li>{@code attachment}: An optional file to attach to the email.</li>
 * </ul>
 *
 * <p><b>Validation:</b></p>
 * <ul>
 *     <li>{@code @NotEmpty} ensures that the {@code to} field contains at least one recipient.</li>
 *     <li>{@code @NotNull} ensures that the {@code subject} and {@code content} fields are not null.</li>
 * </ul>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * FenixEmailRequest request = new FenixEmailRequest();
 * request.setTo(List.of("recipient@example.com"));
 * request.setSubject("Test Subject");
 * request.setContent("<p>This is a test email.</p>");
 * request.setHtml(true);
 * request.setAttachment(new File("/path/to/file.pdf"));
 *
 * System.out.println(request);
 * }</pre>
 *
 * <p><b>Example JSON Representation:</b></p>
 * <pre>
 * {
 *     "to": ["recipient1@example.com", "recipient2@example.com"],
 *     "subject": "Test Email",
 *     "content": "This is the email content.",
 *     "isHtml": true,
 *     "attachment": "/path/to/file.pdf"
 * }
 * </pre>
 *
 * <p><b>Integration:</b></p>
 * <p>
 * This class is typically used in methods such as {@code sendJson(FenixEmailRequest)} in the
 * {@link com.fenix.fenix_mail_service.service.FenixMailService}.
 * </p>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FenixEmailRequest {

    @NotEmpty
    private List<String> to;

    @NotNull
    private String subject;

    @NotNull
    private String content;

    private boolean isHtml;
    private File attachment;
}
