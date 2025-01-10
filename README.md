# **MailService Help Documentation**

This document provides detailed instructions on how to use the `MailService` and configure it in your Spring Boot project.

---

## **Overview**
The `MailService` is a simple and intuitive utility for sending emails. It supports:
- Sending plain text and HTML emails.
- Sending emails to multiple recipients.
- Adding attachments to emails.
- Easy configuration via `application.properties` or `application.yml` using the prefix `fenix.spring.mail`.

---

## **Methods**

### **1. `sendEmail`**
Sends an email to one or more recipients with either plain text or HTML content.

#### **Parameters**
- `List<String> to`: A list of recipient email addresses.
- `String subject`: The subject of the email.
- `String content`: The body of the email, which can be plain text or HTML.
- `boolean isHtml`: Indicates whether the email content is in HTML format (`true`) or plain text (`false`).

#### **Return Value**
- Returns `true` if the email is sent successfully.
- Throws a `RuntimeException` with a detailed error message if the operation fails.

#### **Example Usage**
```java
boolean isSent = mailService.sendEmail(
    List.of("recipient1@example.com", "recipient2@example.com"),
    "Welcome",
    "<h1>Welcome to our service!</h1>",
    true
);
System.out.println("Email sent: " + isSent);
```

---

### **2. `sendEmailWithAttachment`**
Sends an email to one or more recipients with either plain text or HTML content and an optional file attachment.

#### **Parameters**
- `List<String> to`: A list of recipient email addresses.
- `String subject`: The subject of the email.
- `String content`: The body of the email, which can be plain text or HTML.
- `boolean isHtml`: Indicates whether the email content is in HTML format (`true`) or plain text (`false`).
- `File attachment`: A file to be attached to the email (can be `null` if no attachment is needed).

#### **Return Value**
- Returns `true` if the email is sent successfully.
- Throws a `RuntimeException` with a detailed error message if the operation fails.

#### **Example Usage**
```java
File attachment = new File("path/to/file.pdf");
boolean isSent = mailService.sendEmailWithAttachment(
    List.of("recipient1@example.com", "recipient2@example.com"),
    "Report",
    "<p>Here is the requested report.</p>",
    true,
    attachment
);
System.out.println("Email sent with attachment: " + isSent);
```

---

## **Configuration**

The `MailService` uses custom configuration properties prefixed with `fenix.spring.mail`. These can be added to your `application.properties` or `application.yml` file.

### **Properties Configuration**
Add the following properties to your `application.properties`:

```properties
fenix.spring.mail.host=smtp.gmail.com
fenix.spring.mail.port=587
fenix.spring.mail.username=your_email@gmail.com
fenix.spring.mail.password=your_password
fenix.spring.mail.auth=true
fenix.spring.mail.starttls.enable=true
```

### **YAML Configuration**
Alternatively, you can configure the mail server in `application.yml`:

```yaml
fenix:
  spring:
    mail:
      host: smtp.gmail.com
      port: 587
      username: your_email@gmail.com
      password: your_password
      auth: true
      starttls:
        enable: true
```

---

## **Initialization**

The `MailService` is a Spring-managed component. To use it in your application, simply inject it into your class as a dependency.

#### **Example of Dependency Injection**
```java
import com.fenix.fenix_mail_service.service.MailService;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final MailService mailService;

    public NotificationService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendWelcomeEmail() {
        mailService.sendEmail(
            List.of("user@example.com"),
            "Welcome!",
            "<h1>Thank you for joining us!</h1>",
            true
        );
    }
}
```

---

## **Error Handling**

### **RuntimeException**
If an error occurs during email sending (e.g., invalid recipient address, SMTP server issue), the methods throw a `RuntimeException`. This exception includes a detailed error message for debugging.

### **Example of Error Handling**
The exceptions are already handled internally, so the user does not need to handle them explicitly. If needed, the user can log or process the errors.

#### **Example**
```java
try {
    mailService.sendEmail(
        List.of("recipient@example.com"),
        "Test Email",
        "This is a test.",
        false
    );
    System.out.println("Email sent successfully!");
} catch (RuntimeException e) {
    System.err.println("Failed to send email: " + e.getMessage());
}
```

---

## **Prerequisites**

Before using the `MailService`, ensure the following:
1. You have a valid email server (e.g., Gmail, Outlook) and its SMTP settings.
2. Your email server allows SMTP access.
3. For Gmail, ensure "Less secure app access" or App Password is configured if needed.

---

## **FAQ**

### **Q: Can I use MailService with different SMTP servers?**
Yes, you can configure any SMTP server by adjusting the `fenix.spring.mail` properties in `application.properties` or `application.yml`.

### **Q: What happens if I don't provide valid credentials?**
If the credentials are invalid, a `RuntimeException` will be thrown with an appropriate error message from the SMTP server.

---

If you have further questions or issues, feel free to contact the development team.

