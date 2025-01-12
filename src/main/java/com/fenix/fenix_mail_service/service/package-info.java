/**
 * This package contains the core service classes for the Fenix Mail Service.
 * <p>
 * These services handle the primary functionality of the application, including
 * sending emails and managing email logs. The services interact with other
 * components such as configuration properties and data models to ensure
 * smooth email operations.
 * </p>
 *
 * <p><b>Classes:</b></p>
 * <ul>
 *     <li>{@link com.fenix.fenix_mail_service.service.FenixMailService}</li>
 *     <li>{@link com.fenix.fenix_mail_service.service.FenixLogService}</li>
 * </ul>
 *
 * <p><b>Class Descriptions:</b></p>
 *
 * <p><b>FenixMailService:</b></p>
 * This service provides methods for sending emails with various options:
 * <ul>
 *     <li>Sending plain text or HTML emails.</li>
 *     <li>Adding optional attachments to emails.</li>
 *     <li>Logging email activity into a JSON file for auditing purposes.</li>
 * </ul>
 *
 * <p><b>FenixLogService:</b></p>
 * This service manages the logging of email activity:
 * <ul>
 *     <li>Maintains a log of all sent emails in a JSON file.</li>
 *     <li>Provides methods to save and read email logs.</li>
 *     <li>Ensures that logs are written in a structured format for easy retrieval and analysis.</li>
 * </ul>
 *
 * <p><b>Integration:</b></p>
 * <p>
 * These services are Spring-managed beans and can be injected into other
 * components or controllers using {@code @Autowired}.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * @Autowired
 * private FenixMailService mailService;
 *
 * public void sendTestEmail() {
 *     FenixEmailRequest request = new FenixEmailRequest();
 *     request.setTo(List.of("recipient@example.com"));
 *     request.setSubject("Test Email");
 *     request.setContent("This is a test email.");
 *     request.setHtml(false);
 *
 *     mailService.sendJson(request);
 * }
 * }</pre>
 *
 * <pre>{@code
 * @Autowired
 * private FenixLogService logService;
 *
 * public void retrieveLogs() {
 *     List<FenixEmailLog> logs = logService.readEmailLogs();
 *     logs.forEach(System.out::println);
 * }
 * }</pre>
 */
package com.fenix.fenix_mail_service.service;