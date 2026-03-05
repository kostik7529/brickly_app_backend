package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private LocalDateTime createdAt;
}
