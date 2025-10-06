package com.example.Java_Web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> "Field '" + fe.getField() + "' " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));

        Map<String, Object> body = Map.of(
                "status", 400,
                "error", "Bad Request",
                "message", errors,
                "path", request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(body);
    }
}
