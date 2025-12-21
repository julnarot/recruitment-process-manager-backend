package com.seek.rpm.customer.infrastructure.web.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.seek.rpm.customer.application.dto.CustomResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponse<String>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {
        CustomResponse<String> response = CustomResponse.<String>builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        CustomResponse<Map<String, String>> response = CustomResponse.<Map<String, String>>builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.valueOf(422))
                .statusCode(HttpStatus.valueOf(422).value())
                .message("Validation failed")
                .data(errors)
                .build();

        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CustomResponse<Void>> handleNotFound(
            NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        CustomResponse.<Void>builder()
                                .timeStamp(LocalDateTime.now())
                                .status(HttpStatus.NOT_FOUND)
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .message("Resource not found")

                                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomResponse<Void>> handleInvalidFormat(
            HttpMessageNotReadableException ex) {

        String message = "Malformed JSON request";

        Throwable root = ex.getMostSpecificCause();

        if (root instanceof DateTimeParseException) {
            message = "Invalid date format in date field. Expected format is yyyy-MM-dd";
        }

        return ResponseEntity.badRequest()
                .body(
                        CustomResponse.<Void>builder()
                                .timeStamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST)
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message(message)

                                .build());
    }

}