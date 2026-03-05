package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "element")
public class Element {
    @Id
    @Column(name = "element_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "part_num", nullable = false)
    private Part part;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @Column(name = "design_id")
    private Integer designId;
}
