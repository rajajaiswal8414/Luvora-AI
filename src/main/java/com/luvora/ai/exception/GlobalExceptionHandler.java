package com.luvora.ai.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());

        log.error("BadRequestException: {}", e.getMessage());

        return ResponseEntity
                .status(apiError.status())
                .body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException e) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        log.error("ResourceNotFound: {}", e.getMessage());

        return ResponseEntity
                .status(apiError.status())
                .body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<ApiFieldError> apiFieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiFieldError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                apiFieldErrors
        );

        log.error("Validation failed: {}", apiFieldErrors, ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }



}
