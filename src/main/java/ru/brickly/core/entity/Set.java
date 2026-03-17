package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "set")
public class Set {
    @Id
    @Column(name = "set_num")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @Column(name = "num_parts")
    private Integer numParts;
}