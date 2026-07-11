package com.luizjacomn.patmanapi.shared.exception.handler;

import com.luizjacomn.patmanapi.shared.constant.ErrorConstants;
import com.luizjacomn.patmanapi.shared.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {
        List<ValidationError> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> new ValidationError(
                error.getField(),
                error.getDefaultMessage())
            )
            .sorted(Comparator.comparing(ValidationError::field))
            .toList();

        ErrorResponse response = new ErrorResponse(
            ErrorType.VALIDATION_FAILED,
            request.getRequestURI(),
            errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                         HttpServletRequest request) {

        log.error("Not found for filter: {}", ex.getFilter());

        ErrorResponse response = new ErrorResponse(
            ErrorType.NOT_FOUND,
            ex.getMessage(),
            request.getRequestURI(),
            List.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex,
                                                         HttpServletRequest request) {

        log.error("Unexpected error", ex);

        ErrorResponse response = new ErrorResponse(
            ErrorType.ERROR,
            ErrorConstants.Messages.UNKNOWN_ERROR,
            request.getRequestURI(),
            List.of()
        );

        return ResponseEntity.internalServerError().body(response);
    }

}
