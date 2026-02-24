package ru.brickly.core.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private String city;
}
