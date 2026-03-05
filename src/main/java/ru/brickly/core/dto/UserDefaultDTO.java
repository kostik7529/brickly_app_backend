package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDefaultDTO {
    private long id;
    private String username;
    private String name;
    private LocalDateTime createdAt;
    private String email;
    private String city;
}
