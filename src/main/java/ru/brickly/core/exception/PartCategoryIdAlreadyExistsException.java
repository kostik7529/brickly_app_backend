package ru.brickly.core.exception;

public class PartCategoryIdAlreadyExistsException extends RuntimeException {
    public PartCategoryIdAlreadyExistsException(String message) {
        super(message);
    }
}
