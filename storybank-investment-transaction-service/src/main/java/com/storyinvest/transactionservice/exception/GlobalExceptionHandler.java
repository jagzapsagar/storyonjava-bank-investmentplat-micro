package com.storyinvest.transactionservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // -------------------------
    // 1. Account Not Found
    // -------------------------
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAccountNotFound(AccountNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Account Not Found");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // -------------------------
    // 2. Insufficient Balance
    // -------------------------
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientBalance(InsufficientBalanceException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Insufficient Balance");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // -------------------------
    // 3. Invalid Transaction Type
    // -------------------------
    @ExceptionHandler(InvalidTransactionTypeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidType(InvalidTransactionTypeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid Transaction Type");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // -------------------------
    // 4. Validation Errors (@Valid)
    // -------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // -------------------------
    // 5. Generic Fallback Exception
    // -------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralErrors(Exception ex) {
        ex.printStackTrace();
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
