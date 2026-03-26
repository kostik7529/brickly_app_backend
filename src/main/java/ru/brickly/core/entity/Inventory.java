package ru.brickly.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "version")
    private int version;

    @Column(name = "set_num")
    private String ownerId;
}
