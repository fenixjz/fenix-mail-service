# Fenix Mail Service

The Fenix Mail Service is a lightweight service for sending emails with support for logging email history into a JSON file. It provides methods to send plain text and HTML emails, with or without attachments. This document explains how to integrate this service into another Spring Boot application using JitPack and configure it properly.

---

## Table of Contents

1. [Installation](#installation)
2. [Configuration](#configuration)
3. [Available Methods](#available-methods)
4. [Usage](#usage)

---

## Installation

To integrate the Fenix Mail Service into your Spring Boot project, add the dependency from JitPack to your `pom.xml`.

### Step 1: Add JitPack Repository
Add the JitPack repository to your `repositories` section in `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### Step 2: Add Fenix Mail Service Dependency
Add the Fenix Mail Service dependency:

```xml
<dependency>
    <groupId>com.github.fenixjz</groupId>
    <artifactId>fenix-mail-service</artifactId>
    <version>v1.1.6</version>
</dependency>
```

Replace `your-username` with the GitHub username where the service is hosted, and update the version as needed.

---

## Configuration

The Fenix Mail Service requires specific properties to be added to the `application.properties` or `application.yml` file in your Spring Boot project.

### Example Configuration (application.properties):

```properties
fenix.spring.mail.host=your-smtp-host
fenix.spring.mail.port=your-port
fenix.spring.mail.username=your-username
fenix.spring.mail.password=your-password
fenix.spring.mail.from-address=your_email@email.com
fenix.spring.mail.auth=true
fenix.spring.mail.starttls.enable=true
fenix.spring.mail.default-encoding=UTF-8
fenix.spring.mail.log-path=C:/uploads/mail_log.json
```

### Explanation of Properties:

| Property                          | Description                                           |
|-----------------------------------|-------------------------------------------------------|
| `fenix.spring.mail.host`          | SMTP server host (e.g., MailJet, Gmail, etc.)         |
| `fenix.spring.mail.port`          | SMTP server port                                      |
| `fenix.spring.mail.username`      | SMTP username                                         |
| `fenix.spring.mail.password`      | SMTP password                                         |
| `fenix.spring.mail.from-address`  | Default email address used as the sender             |
| `fenix.spring.mail.auth`          | Enable SMTP authentication                           |
| `fenix.spring.mail.starttls.enable` | Enable STARTTLS                                      |
| `fenix.spring.mail.default-encoding` | Default email encoding                               |
| `fenix.spring.mail.log-path`      | Path to the JSON file for logging email history      |

---

## Available Methods

### `sendEmail`

Sends an email to one or more recipients with plain text or HTML content.

#### Parameters:
- `to` (List<String>): A list of recipient email addresses.
- `subject` (String): The subject of the email.
- `content` (String): The body content of the email, which can be plain text or HTML.
- `isHtml` (boolean): A flag indicating whether the content is HTML (true) or plain text (false).

#### Returns:
- `boolean`: `true` if the email is successfully sent.

#### Example:
```java
mailService.sendEmail(
    List.of("recipient@example.com"),
    "Test Subject",
    "<p>This is a test email.</p>",
    true
);
```

### `sendEmailWithAttachment`

Sends an email to one or more recipients with plain text or HTML content and an attachment.

#### Parameters:
- `to` (List<String>): A list of recipient email addresses.
- `subject` (String): The subject of the email.
- `content` (String): The body content of the email, which can be plain text or HTML.
- `isHtml` (boolean): A flag indicating whether the content is HTML (true) or plain text (false).
- `attachment` (File): A file to be attached to the email; can be `null` if no attachment is needed.

#### Returns:
- `boolean`: `true` if the email is successfully sent.

#### Example:
```java
File attachment = new File("/path/to/file.pdf");
mailService.sendEmailWithAttachment(
    List.of("recipient@example.com"),
    "Test Subject",
    "<p>This is a test email with attachment.</p>",
    true,
    attachment
);
```

### `readEmailLogs`

Reads the email log entries from the JSON file.

#### Returns:
- `List<EmailLog>`: A list of `EmailLog` objects representing the history of sent emails.

#### Example:
```java
List<EmailLog> emailLogs = logService.readEmailLogs();
emailLogs.forEach(System.out::println);
```

---

## Usage

1. Autowire the `MailService` and/or `LogService` into your Spring components where needed.
2. Configure the properties in your `application.properties` or `application.yml` file.
3. Use the available methods to send emails and manage logs.

#### Example Integration:
```java
@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        boolean isSent = mailService.sendEmail(
            request.getRecipients(),
            request.getSubject(),
            request.getContent(),
            request.isHtml()
        );
        return isSent ? ResponseEntity.ok("Email sent successfully") : ResponseEntity.status(500).body("Failed to send email");
    }
}
```

---

## License

This project is licensed under the MIT License.
