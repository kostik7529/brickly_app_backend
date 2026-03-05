package ru.brickly.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "minifig")
public class Minifig {
    @Id
    @Column(name = "fig_num")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "num_parts")
    private int numParts;

    @Column(name = "img_url")
    private String imageUrl;
}
