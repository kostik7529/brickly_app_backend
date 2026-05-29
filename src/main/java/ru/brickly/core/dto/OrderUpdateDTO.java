package ru.brickly.core.dto;

import lombok.Data;

@Data
public class OrderUpdateDTO {
    private String shippingMethod;
    private String shippingAddress;
}
