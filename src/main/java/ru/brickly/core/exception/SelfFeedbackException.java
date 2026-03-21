package ru.brickly.core.exception;

public class SelfFeedbackException extends RuntimeException {
    public SelfFeedbackException(String message) {
        super(message);
    }
}
