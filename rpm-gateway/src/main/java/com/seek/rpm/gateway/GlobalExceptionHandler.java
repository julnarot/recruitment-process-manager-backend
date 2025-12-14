package com.seek.rpm.gateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleJwtException(JwtAuthenticationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage()); // "Token inválido o ausente"
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
