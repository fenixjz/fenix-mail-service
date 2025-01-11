package com.fenix.fenix_mail_service.service;

import com.fenix.fenix_mail_service.component.MailProperties;
import com.fenix.fenix_mail_service.model.EmailLog;
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
public class MailService {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;
    private final LogService logService;

    /**
     * Sends an email to one or more recipients with plain text or HTML content.
     *
     * @param to      a list of recipient email addresses
     * @param subject the subject of the email
     * @param content the body content of the email, which can be plain text or HTML
     * @param isHtml  a flag indicating whether the content is HTML (true) or plain text (false)
     * @return true if the email is successfully sent
     * @throws RuntimeException if there is an error while sending the email
     */
    public boolean sendEmail(List<String> to, String subject, String content, boolean isHtml) {
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipients(to);
        emailLog.setSubject(subject);
        emailLog.setBody(content);
        emailLog.setSentAt(LocalDateTime.now());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setFrom(mailProperties.getFromAddress());
            helper.setTo(to.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            mailSender.send(message);
            emailLog.setSuccess(true);
            logService.saveEmailLog(emailLog);
            return true;
        } catch (MessagingException e) {
            emailLog.setSuccess(false);
            logService.saveEmailLog(emailLog);
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    /**
     * Sends an email to one or more recipients with plain text or HTML content and an attachment.
     *
     * @param to         a list of recipient email addresses
     * @param subject    the subject of the email
     * @param content    the body content of the email, which can be plain text or HTML
     * @param isHtml     a flag indicating whether the content is HTML (true) or plain text (false)
     * @param attachment a file to be attached to the email; can be null if no attachment is needed
     * @return true if the email is successfully sent
     * @throws RuntimeException if there is an error while sending the email
     */
    public boolean sendEmailWithAttachment(
            List<String> to, String subject, String content, boolean isHtml, File attachment) {
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipients(to);
        emailLog.setSubject(subject);
        emailLog.setBody(content);
        emailLog.setSentAt(LocalDateTime.now());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(mailProperties.getFromAddress());
            helper.setTo(to.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            if (attachment != null && attachment.exists()) {
                helper.addAttachment(attachment.getName(), attachment);
            }

            mailSender.send(message);
            emailLog.setSuccess(true);
            logService.saveEmailLog(emailLog);
            return true;
        } catch (MessagingException e) {
            emailLog.setSuccess(false);
            logService.saveEmailLog(emailLog);
            throw new RuntimeException("Failed to send email with attachment: " + e.getMessage(), e);
        }
    }
}
