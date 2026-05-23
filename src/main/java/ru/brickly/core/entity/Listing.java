package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "listing")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private String status;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "condition")
    private String condition;

    @Column(name = "condition_rate")
    private int conditionRate;

    @Column(name = "price")
    private int price;

    @Column(name = "views_count")
    private int viewsCount;
}
