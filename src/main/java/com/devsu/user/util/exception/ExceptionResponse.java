package com.devsu.user.util.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class ExceptionResponse {
    private static final String PATTERN_FORMAT = "yyyy-MM-dd hh:mm:ss zzz";
    private HttpStatus status;
    private String timeStamp;
    private String message;
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(PATTERN_FORMAT).withZone(ZoneId.of("-5"));

    public ExceptionResponse(HttpStatus status, String message) {

        this.timeStamp = formatter.format(Instant.now());
        this.status = status;
        this.message = message;
    }
}