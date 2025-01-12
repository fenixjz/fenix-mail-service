package com.fenix.fenix_mail_service.service;

import com.fenix.fenix_mail_service.component.FenixMailProperties;
import com.fenix.fenix_mail_service.model.EmailRequest;
import com.fenix.fenix_mail_service.model.FenixEmailLog;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FenixMailService {

    private final JavaMailSender mailSender;
    private final FenixMailProperties fenixMailProperties;
    private final FenixLogService fenixLogService;

    /**
     * Sends an email to the specified recipients.
     * <p>
     * This method sends an email with the provided subject, content, and optional attachment.
     * The email can be formatted as HTML or plain text based on the {@code isHtml} flag.
     * </p>
     *
     * <p><b>Required Parameters:</b></p>
     * <ul>
     *     <li><b>to:</b> A list of recipient email addresses (must not be null or empty).</li>
     *     <li><b>subject:</b> The subject of the email (must not be null or empty).</li>
     *     <li><b>content:</b> The content of the email (must not be null or empty).</li>
     * </ul>
     *
     * <p><b>Optional Parameters:</b></p>
     * <ul>
     *     <li><b>isHtml:</b> A boolean flag indicating if the email content is HTML (default is false).</li>
     *     <li><b>attachment:</b> An optional file to attach to the email (can be null or non-existent).</li>
     * </ul>
     *
     * @param to A list of recipient email addresses (required).
     * @param subject The subject of the email (required).
     * @param content The content of the email (required).
     * @param isHtml Whether the content is HTML or plain text (optional).
     * @param attachment An optional attachment file to include in the email.
     * @return {@code true} if the email was successfully sent, {@code false} otherwise.
     * @throws RuntimeException If the email could not be sent due to a {@link MessagingException}.
     */
    public boolean send(List<String> to, String subject, String content, boolean isHtml, File attachment) {
        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException("Recipient list 'to' must not be null or empty.");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject must not be null or empty.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content must not be null or empty.");
        }

        FenixEmailLog fenixEmailLog = new FenixEmailLog();
        fenixEmailLog.setRecipients(to);
        fenixEmailLog.setSubject(subject);
        fenixEmailLog.setBody(content);
        fenixEmailLog.setSentAt(LocalDateTime.now());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, attachment != null, "UTF-8");
            helper.setFrom(fenixMailProperties.getFromAddress());
            helper.setTo(to.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            if (attachment != null && attachment.exists()) {
                helper.addAttachment(attachment.getName(), attachment);
            }

            mailSender.send(message);
            fenixEmailLog.setSuccess(true);
            fenixLogService.saveEmailLog(fenixEmailLog);
            return true;
        } catch (MessagingException e) {
            fenixEmailLog.setSuccess(false);
            fenixLogService.saveEmailLog(fenixEmailLog);
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    /**
     * Sends an email using the details provided in the {@link EmailRequest} object.
     * <p>
     * This method validates the {@link EmailRequest} object, constructs an email message,
     * and sends it using the {@code JavaMailSender}. Additionally, it logs the email details
     * (e.g., recipients, subject, and content) in a {@link FenixEmailLog}.
     * </p>
     *
     * <p><b>Steps performed:</b></p>
     * <ul>
     *     <li>Validates the {@link EmailRequest} object using {@link jakarta.validation.Valid}.</li>
     *     <li>Creates a new {@link FenixEmailLog} instance and populates it with email details.</li>
     *     <li>Constructs a MIME message using {@link MimeMessageHelper}, including optional attachments.</li>
     *     <li>Sends the email using {@code JavaMailSender}.</li>
     *     <li>Saves the email log (success or failure) using {@code fenixLogService}.</li>
     * </ul>
     *
     * <p><b>Parameters:</b></p>
     * <ul>
     *     <li><b>request:</b> A {@link EmailRequest} object containing the email details.
     *         <ul>
     *             <li>{@code to}: A list of recipient email addresses (required, must not be empty).</li>
     *             <li>{@code subject}: The subject of the email (required, must not be null).</li>
     *             <li>{@code content}: The body of the email (required, must not be null).</li>
     *             <li>{@code isHtml}: A boolean flag indicating whether the content is in HTML format.</li>
     *             <li>{@code attachment}: An optional file to be attached to the email.</li>
     *         </ul>
     *     </li>
     * </ul>
     *
     * <p><b>Return Value:</b></p>
     * <ul>
     *     <li>{@code true} if the email was successfully sent.</li>
     *     <li>Throws a {@link RuntimeException} if an error occurs while sending the email.</li>
     * </ul>
     *
     * <p><b>Exception Handling:</b></p>
     * <ul>
     *     <li>If a {@link MessagingException} occurs, the method logs the failure and throws a
     *         {@link RuntimeException} with the error details.</li>
     * </ul>
     *
     * <p><b>Dependencies:</b></p>
     * <ul>
     *     <li>{@code mailSender}: Sends the constructed MIME email.</li>
     *     <li>{@code fenixMailProperties}: Provides email configuration details such as the sender address.</li>
     *     <li>{@code fenixLogService}: Persists the email log details.</li>
     * </ul>
     *
     * @param request The {@link EmailRequest} object containing email details (must be valid).
     * @return {@code true} if the email is successfully sent.
     * @throws RuntimeException If a {@link MessagingException} occurs during email sending.
     */

    public boolean sendJson(@Valid EmailRequest request) {
        FenixEmailLog fenixEmailLog = new FenixEmailLog();
        fenixEmailLog.setRecipients(request.getTo());
        fenixEmailLog.setSubject(request.getSubject());
        fenixEmailLog.setBody(request.getContent());
        fenixEmailLog.setSentAt(LocalDateTime.now());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, request.getAttachment() != null, "UTF-8");
            helper.setFrom(fenixMailProperties.getFromAddress());
            helper.setTo(request.getTo().toArray(new String[0]));
            helper.setSubject(request.getSubject());
            helper.setText(request.getContent(), request.isHtml());

            if (request.getAttachment() != null && request.getAttachment().exists()) {
                helper.addAttachment(request.getAttachment().getName(), request.getAttachment());
            }

            mailSender.send(message);
            fenixEmailLog.setSuccess(true);
            fenixLogService.saveEmailLog(fenixEmailLog);
            return true;
        } catch (MessagingException e) {
            fenixEmailLog.setSuccess(false);
            fenixLogService.saveEmailLog(fenixEmailLog);
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
