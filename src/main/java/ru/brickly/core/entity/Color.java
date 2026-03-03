package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "rgb")
    private String rgb;

    @Column(name = "is_trans")
    private boolean isTransparent;

    @Column(name = "num_parts")
    private Integer numParts;

    @Column(name = "num_sets")
    private Integer numSets;

    @Column(name = "y1")
    private Integer yearSince;

    @Column(name = "y2")
    private Integer yearTill;
}
