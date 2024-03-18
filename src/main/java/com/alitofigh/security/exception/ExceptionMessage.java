package com.alitofigh.security.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by A_Tofigh at 3/8/2024
 */

@Data
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String path;

    public ExceptionMessage() {
        timestamp = LocalDateTime.now();
    }

    public ExceptionMessage(String message, String path) {
        this();
        this.message = message;
        this.path = path;
    }
// getters and setters left out
}
