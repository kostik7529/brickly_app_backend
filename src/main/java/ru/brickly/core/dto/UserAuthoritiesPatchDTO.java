package ru.brickly.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAuthoritiesPatchDTO {
    List<String> authorities;
}
