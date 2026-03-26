package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private OffsetDateTime createdAt;
}
