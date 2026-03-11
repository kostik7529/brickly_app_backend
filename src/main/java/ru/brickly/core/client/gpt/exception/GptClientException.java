package ru.brickly.core.client.gpt.exception;

public class GptClientException extends RuntimeException {
    public GptClientException(String message) {
        super(message);
    }

    public GptClientException(String message, Throwable cause) {
        super(message, cause);
    }
}