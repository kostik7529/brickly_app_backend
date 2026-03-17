package ru.brickly.core.exception;

public class SetIdAlreadyExistsException extends RuntimeException {
    public SetIdAlreadyExistsException(String message) {
        super(message);
    }
}
