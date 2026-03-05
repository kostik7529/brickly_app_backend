package ru.brickly.core.exception;

public class PartIdAlreadyExistsException extends RuntimeException {
    public PartIdAlreadyExistsException(String message) {
        super(message);
    }
}
