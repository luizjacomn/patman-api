package com.luizjacomn.patmanapi.shared.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public record ErrorResponse(
    ZonedDateTime timestamp,
    int statusCode,
    String status,
    String message,
    String path,
    List<ValidationError> errors
) {

    public ErrorResponse(ErrorType errorType, String path, List<ValidationError> errors) {
        this(
            ZonedDateTime.now(ZoneOffset.UTC),
            errorType.getStatusCode(),
            errorType.getStatusMessage(),
            errorType.getMessage(),
            path,
            errors
        );
    }

    public ErrorResponse(ErrorType errorType, String message, String path, List<ValidationError> errors) {
        this(
            ZonedDateTime.now(ZoneOffset.UTC),
            errorType.getStatusCode(),
            errorType.getStatusMessage(),
            message,
            path,
            errors
        );
    }

}
