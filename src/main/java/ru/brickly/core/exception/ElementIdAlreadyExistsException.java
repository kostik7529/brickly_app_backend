package ru.brickly.core.exception;

public class ElementIdAlreadyExistsException extends RuntimeException {
    public ElementIdAlreadyExistsException(String message) {
        super(message);
    }
}
