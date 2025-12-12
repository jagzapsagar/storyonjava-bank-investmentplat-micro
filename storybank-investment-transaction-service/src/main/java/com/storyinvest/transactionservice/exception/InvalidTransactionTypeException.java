package com.storyinvest.transactionservice.exception;

public class InvalidTransactionTypeException extends RuntimeException {
    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
