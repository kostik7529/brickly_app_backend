package ru.brickly.core.exception;

public class InventoryIdAlreadyExistsException extends RuntimeException {
    public InventoryIdAlreadyExistsException(String message) {
        super(message);
    }
}
