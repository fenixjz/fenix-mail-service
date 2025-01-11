package com.fenix.fenix_mail_service.service;

import com.fenix.fenix_mail_service.component.FenixMailProperties;
import com.fenix.fenix_mail_service.model.FenixEmailLog;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
     * Sends an email to one or more recipients with plain text or HTML content and optionally an attachment.
     *
     * @param to         a list of recipient email addresses
     * @param subject    the subject of the email
     * @param content    the body content of the email, which can be plain text or HTML
     * @param isHtml     a flag indicating whether the content is HTML (true) or plain text (false)
     * @param attachment a file to be attached to the email; can be null if no attachment is needed
     * @return true if the email is successfully sent
     * @throws RuntimeException if there is an error while sending the email
     */
    public boolean send(List<String> to, String subject, String content, boolean isHtml, File attachment) {
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
}
