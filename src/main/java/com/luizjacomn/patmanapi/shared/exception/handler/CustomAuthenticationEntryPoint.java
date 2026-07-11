package com.luizjacomn.patmanapi.shared.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizjacomn.patmanapi.shared.constant.ErrorConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        ErrorResponse error = new ErrorResponse(
            ErrorType.UNAUTHORIZED,
            ErrorConstants.Messages.UNAUTHORIZED,
            request.getRequestURI(),
            List.of()
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getOutputStream(), error);
    }

}
