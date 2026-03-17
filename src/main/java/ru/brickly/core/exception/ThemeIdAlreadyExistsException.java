package ru.brickly.core.exception;

public class ThemeIdAlreadyExistsException extends RuntimeException {
    public ThemeIdAlreadyExistsException(String message) {
        super(message);
    }
}
