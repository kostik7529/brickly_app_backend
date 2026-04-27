package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory_minifig")
public class InventoryMinifig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "fig_num")
    private Minifig minifig;

    @Column(name = "quantity")
    private int quantity;
}
