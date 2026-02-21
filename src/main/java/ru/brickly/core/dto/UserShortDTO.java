package ru.brickly.core.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UserShortDTO {
    private long id;
    private String username;
    private String name;
}
