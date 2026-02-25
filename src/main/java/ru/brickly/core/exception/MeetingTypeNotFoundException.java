package ru.brickly.core.exception;

public class MeetingTypeNotFoundException extends RuntimeException {
    public MeetingTypeNotFoundException(String message) {
        super(message);
    }
}
