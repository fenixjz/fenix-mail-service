# Fenix Mail Service

Fenix Mail Service is a lightweight, flexible service for sending emails with support for logging email history into a JSON file. It supports sending plain text and HTML emails, with or without attachments. This README will guide you through integrating the service into a Spring Boot application and configuring it effectively.

---

## Table of Contents

1. [Installation](#installation)
2. [Configuration](#configuration)
3. [Available Methods](#available-methods)
4. [Usage](#usage)
5. [License](#license)

---

## Installation

To integrate Fenix Mail Service into your Spring Boot project, add the dependency from JitPack to your `pom.xml` file.

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

Replace `fenixjz` with the appropriate GitHub username hosting the service, and update the version as needed.

---

## Configuration

Fenix Mail Service requires specific properties to be set in your `application.properties` or `application.yml` file.

### Example Configuration (application.properties):

```properties
fenix.spring.mail.host=smtp.example.com
fenix.spring.mail.port=587
fenix.spring.mail.username=your-username
fenix.spring.mail.password=your-password
fenix.spring.mail.from-address=no-reply@example.com
fenix.spring.mail.auth=true
fenix.spring.mail.starttls.enable=true
fenix.spring.mail.default-encoding=UTF-8
fenix.spring.mail.log-path=/var/logs/mail_log.json
```

### Property Descriptions:

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

### `send`

Sends an email to one or more recipients with plain text or HTML content.

#### Parameters:
- `to` (List<String>): A list of recipient email addresses.
- `subject` (String): The subject of the email.
- `content` (String): The body content of the email, which can be plain text or HTML.
- `isHtml` (boolean): A flag indicating whether the content is HTML (`true`) or plain text (`false`).
- `attachment` (File): Optional. A file to be attached to the email. Can be `null`.

#### Returns:
- `boolean`: `true` if the email is successfully sent.

#### Example:
```java
File attachment = new File("/path/to/file.pdf");
boolean isSent = mailService.send(
    List.of("recipient@example.com"),
    "Test Subject",
    "<p>This is a test email with optional attachment.</p>",
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
List<EmailLog> logs = logService.readEmailLogs();
logs.forEach(System.out::println);
```

---

## Usage

1. **Inject `FenixMailService` and/or `FenixLogService`** into your Spring components where needed.
2. **Set properties** in your `application.properties` or `application.yml` file.
3. **Use available methods** to send emails and manage logs.

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
        boolean isSent = mailService.send(
                request.getRecipients(),
                request.getSubject(),
                request.getContent(),
                request.isHtml(),
                null
        );
        return isSent ? ResponseEntity.ok("Email sent successfully") : ResponseEntity.status(500).body("Failed to send email");
    }
}
```

---

## License

This project is licensed under the MIT License.