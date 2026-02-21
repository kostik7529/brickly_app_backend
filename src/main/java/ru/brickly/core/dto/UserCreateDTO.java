package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UserCreateDTO {
    private String username;
    private String password;
    private LocalTime createdAt;
}
