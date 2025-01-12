package com.fenix.fenix_mail_service.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FenixEmailRequest {

    @NotEmpty
    private List<String> to;

    @NotNull
    private String subject;

    @NotNull
    private String content;

    private boolean isHtml;
    private File attachment;
}
