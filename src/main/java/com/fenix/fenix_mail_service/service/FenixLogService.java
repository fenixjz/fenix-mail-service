package com.fenix.fenix_mail_service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fenix.fenix_mail_service.component.FenixMailProperties;
import com.fenix.fenix_mail_service.model.FenixEmailLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing email logs in the Fenix Mail Service.
 * <p>
 * This class provides functionality to log email transactions into a JSON file and retrieve
 * logged email data for auditing or debugging purposes. It interacts with the
 * {@link FenixMailProperties} to determine the log file path and uses the {@link ObjectMapper}
 * for JSON serialization and deserialization.
 * </p>
 *
 * <p><b>Main Features:</b></p>
 * <ul>
 *     <li>Logs email transactions into a JSON file.</li>
 *     <li>Reads email logs from the JSON file.</li>
 *     <li>Handles JSON file creation and structured logging.</li>
 * </ul>
 *
 * <p><b>Key Methods:</b></p>
 * <ul>
 *     <li>{@link #saveEmailLog(FenixEmailLog)}: Saves a new email log entry into the log file.</li>
 *     <li>{@link #readEmailLogs()}: Reads and returns all email log entries from the log file.</li>
 * </ul>
 *
 * <p><b>Dependencies:</b></p>
 * <ul>
 *     <li>{@link FenixMailProperties}: Provides configuration for the log file path.</li>
 *     <li>{@link ObjectMapper}: Handles JSON serialization and deserialization.</li>
 * </ul>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * @Autowired
 * private FenixLogService logService;
 *
 * public void logEmail(FenixEmailLog emailLog) {
 *     logService.saveEmailLog(emailLog);
 * }
 *
 * public void printEmailLogs() {
 *     List<FenixEmailLog> logs = logService.readEmailLogs();
 *     logs.forEach(System.out::println);
 * }
 * }</pre>
 *
 * <p><b>Notes:</b></p>
 * <ul>
 *     <li>If the log file does not exist, {@link #readEmailLogs()} returns an empty list.</li>
 *     <li>Any errors during file operations throw a {@link RuntimeException} with the error details.</li>
 * </ul>
 *
 * <p><b>Author:</b> Fenix</p>
 * <p><b>Version:</b> 1.X</p>
 * <p><b>Since:</b> 2025-01-12</p>
 */
@Service
@RequiredArgsConstructor
public class FenixLogService {

    private final FenixMailProperties fenixMailProperties;
    private final ObjectMapper objectMapper;

    /**
     * Saves an email log to the JSON file specified in the application configuration.
     * <p>
     * This method reads the current email logs from the JSON file, appends the new
     * {@link FenixEmailLog} entry to the list, and writes the updated list back to the file.
     * </p>
     *
     * <p><b>Steps performed:</b></p>
     * <ul>
     *     <li>Reads the existing email logs from the JSON file using {@code readEmailLogs()}.</li>
     *     <li>Adds the new email log entry to the list.</li>
     *     <li>Writes the updated list to the JSON file located at the path specified
     *         by {@code fenixMailProperties.getLogPath()}.</li>
     * </ul>
     *
     * <p><b>Important Notes:</b></p>
     * <ul>
     *     <li>If the JSON file does not exist, it will be created.</li>
     *     <li>If an error occurs during the write operation, a {@link RuntimeException} is thrown.</li>
     * </ul>
     *
     * <p><b>Dependencies:</b></p>
     * <ul>
     *     <li>{@code objectMapper}: Used for reading and writing JSON data.</li>
     *     <li>{@code fenixMailProperties}: Provides the path to the log file.</li>
     * </ul>
     *
     * @param fenixEmailLog The email log entry to be saved (must not be null).
     * @throws RuntimeException If an {@link IOException} occurs while writing to the JSON file.
     */

    public void saveEmailLog(FenixEmailLog fenixEmailLog) {
        List<FenixEmailLog> fenixEmailLogs = readEmailLogs();

        fenixEmailLogs.add(fenixEmailLog);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fenixMailProperties.getLogPath()), fenixEmailLogs);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    /**
     * Reads email logs from the JSON file specified in the application configuration.
     * <p>
     * This method retrieves a list of {@link FenixEmailLog} entries from a JSON file located
     * at the path specified by {@code fenixMailProperties.getLogPath()}. If the file does not
     * exist, it returns an empty list.
     * </p>
     *
     * <p><b>Steps performed:</b></p>
     * <ul>
     *     <li>Checks if the file exists at the specified path.</li>
     *     <li>If the file does not exist, returns an empty list.</li>
     *     <li>If the file exists, reads the content and deserializes it into a list of
     *         {@link FenixEmailLog} objects using {@code objectMapper}.</li>
     * </ul>
     *
     * <p><b>Important Notes:</b></p>
     * <ul>
     *     <li>If the file does not exist, no exception is thrown, and an empty list is returned.</li>
     *     <li>If an error occurs during the read operation (e.g., invalid JSON format),
     *         a {@link RuntimeException} is thrown.</li>
     * </ul>
     *
     * <p><b>Dependencies:</b></p>
     * <ul>
     *     <li>{@code objectMapper}: Used for reading and deserializing JSON data.</li>
     *     <li>{@code fenixMailProperties}: Provides the path to the log file.</li>
     * </ul>
     *
     * @return A list of {@link FenixEmailLog} entries, or an empty list if the file does not exist.
     * @throws RuntimeException If an {@link IOException} occurs while reading from the JSON file.
     */

    public List<FenixEmailLog> readEmailLogs() {
        if (!Files.exists(Paths.get(fenixMailProperties.getLogPath()))) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(
                    new File(fenixMailProperties.getLogPath()), new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }
}
