package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserDefaultDTO {
    private long id;
    private String username;
    private String name;
    private OffsetDateTime createdAt;
    private String email;
    private String city;
}
