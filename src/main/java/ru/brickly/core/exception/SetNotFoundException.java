package ru.brickly.core.exception;

public class SetNotFoundException extends RuntimeException {
    public SetNotFoundException(String message) { super(message);}

    public SetNotFoundException(String setNum, String message) {
        super("Set with number " + setNum + " not found! " + message);
    }
}