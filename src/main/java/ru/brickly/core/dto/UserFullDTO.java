package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserFullDTO {
    private long id;
    private String username;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private String email;
    private String city;
}
