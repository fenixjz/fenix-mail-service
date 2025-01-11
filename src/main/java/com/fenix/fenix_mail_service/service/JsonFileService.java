package com.fenix.fenix_mail_service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fenix.fenix_mail_service.component.MailProperties;
import com.fenix.fenix_mail_service.model.EmailLog;
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
public class JsonFileService {

    private final MailProperties mailProperties;
    private final ObjectMapper objectMapper;

    /**
     * Save history of email in json file.
     *
     * @param emailLog EmailLog
     */
    public void saveEmailLog(EmailLog emailLog) {
        List<EmailLog> emailLogs = readEmailLogs();

        emailLogs.add(emailLog);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(mailProperties.getLogPath()), emailLogs);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to JSON file", e);
        }
    }

    /**
     * Read email history from json file.
     *
     * @return List<EmailLog>
     */
    public List<EmailLog> readEmailLogs() {
        if (!Files.exists(Paths.get(mailProperties.getLogPath()))) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(
                    new File(mailProperties.getLogPath()), new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from JSON file", e);
        }
    }
}
