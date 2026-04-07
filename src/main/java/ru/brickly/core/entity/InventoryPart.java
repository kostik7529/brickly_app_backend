package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory_part")
public class InventoryPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "part_num")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_spare")
    private boolean isSpare;
}
