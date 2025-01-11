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

@Service
@RequiredArgsConstructor
public class FenixLogService {

    private final FenixMailProperties fenixMailProperties;
    private final ObjectMapper objectMapper;

    /**
     * Saves the email log entry to a JSON file.
     * This method reads the current list of email logs from the JSON file,
     * appends the new email log entry, and writes the updated list back to the file.
     *
     * @param fenixEmailLog An instance of {@link FenixEmailLog} representing the details of the email
     *                 to be logged, such as recipient, subject, body, timestamp, and status.
     * @throws RuntimeException if an error occurs while writing to the JSON file.
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
     * Reads the email log entries from the JSON file.
     * This method checks if the JSON file exists. If it does, it reads the file and
     * deserializes its content into a list of {@link FenixEmailLog} objects. If the file does not exist,
     * it returns an empty list.
     *
     * @return A {@link List} of {@link FenixEmailLog} objects representing the history of sent emails.
     *         Returns an empty list if the log file does not exist.
     * @throws RuntimeException if an error occurs while reading from the JSON file.
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
