package ru.brickly.core.dto;

import lombok.Data;
import ru.brickly.core.entity.CartItem;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UserDefaultDTO {
    private long id;
    private String username;
    private String name;
    private OffsetDateTime createdAt;
    private String email;
    private String city;
    private int balance;
    private List<CartItemDefaultDTO> cartItems;
    private List<AuthorityShortDTO> authorities;
}
