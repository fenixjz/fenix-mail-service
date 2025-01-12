/**
 * This package contains data models (DTOs) used by the Fenix Mail Service.
 * <p>
 * These classes represent the structure of the data exchanged within the mail service.
 * They include email requests and logs, ensuring a standardized way of handling
 * email-related operations.
 * </p>
 *
 * <p><b>Classes:</b></p>
 * <ul>
 *     <li>{@link com.fenix.fenix_mail_service.model.FenixEmailRequest}</li>
 *     <li>{@link com.fenix.fenix_mail_service.model.FenixEmailLog}</li>
 * </ul>
 *
 * <p><b>Class Descriptions:</b></p>
 *
 * <p><b>FenixEmailRequest:</b></p>
 * Represents the details of an email to be sent, including:
 * <ul>
 *     <li>{@code to}: A list of recipient email addresses (required).</li>
 *     <li>{@code subject}: The subject of the email (required).</li>
 *     <li>{@code content}: The body of the email (required).</li>
 *     <li>{@code isHtml}: Indicates whether the content is HTML or plain text.</li>
 *     <li>{@code attachment}: An optional file to attach to the email.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * FenixEmailRequest request = new FenixEmailRequest();
 * request.setTo(List.of("recipient@example.com"));
 * request.setSubject("Test Subject");
 * request.setContent("This is a test email.");
 * request.setHtml(true);
 * request.setAttachment(new File("/path/to/attachment.pdf"));
 * }</pre>
 *
 * <p><b>FenixEmailLog:</b></p>
 * Represents a log entry for an email that was sent, including:
 * <ul>
 *     <li>{@code recipients}: A list of recipient email addresses.</li>
 *     <li>{@code subject}: The subject of the email.</li>
 *     <li>{@code body}: The body of the email.</li>
 *     <li>{@code sentAt}: The timestamp when the email was sent.</li>
 *     <li>{@code success}: A flag indicating whether the email was successfully sent.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * FenixEmailLog log = new FenixEmailLog();
 * log.setRecipients(List.of("recipient@example.com"));
 * log.setSubject("Test Subject");
 * log.setBody("This is a test email body.");
 * log.setSentAt(LocalDateTime.now());
 * log.setSuccess(true);
 * }</pre>
 */
package com.fenix.fenix_mail_service.model;