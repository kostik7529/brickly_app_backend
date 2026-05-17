package ru.brickly.core.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserFullDTO {
    private long id;
    private String username;
    private String name;
    private String password;
    private OffsetDateTime createdAt;
    private String email;
    private String city;
    private int balance;
    private List<AuthorityShortDTO> authorities;
}
