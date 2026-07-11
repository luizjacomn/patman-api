package com.luizjacomn.patmanapi.shared.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "Validation failed"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found"),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final int statusCode;

    private final String statusMessage;

    private final String message;

    ErrorType(HttpStatus status, String message) {
        this.statusCode = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.message = message;
    }

}
