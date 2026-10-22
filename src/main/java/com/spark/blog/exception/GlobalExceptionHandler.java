package com.spark.blog.exception;


import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Objects;

import com.spark.blog.blog.exception.BlogNotFoundException;
import com.spark.falcon.mail.exception.EmailException;
import com.spark.falcon.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleBlogNotFoundException(BlogNotFoundException exception) {
        ApiResponse<String> response = ApiResponse.failure("Blog not found", exception.getMessage()) ;
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailException.class)
    public void handleEmailSendingException(EmailException exception) {
//        @TODO log error
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        ApiResponse<Map<String, String>> response =
                ApiResponse.failure("Validation failed", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception exception) {
        ApiResponse<String> response = ApiResponse.failure("Unexpected error occurred", exception.getMessage());
        log.error("Global Exception handler {}", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
