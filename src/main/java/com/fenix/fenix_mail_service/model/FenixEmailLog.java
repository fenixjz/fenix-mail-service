package com.fenix.fenix_mail_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FenixEmailLog {
    private List<String> recipients;
    private String subject;
    private String body;
    private LocalDateTime sentAt;
    private boolean success;
}
