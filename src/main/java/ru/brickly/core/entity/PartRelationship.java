package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "part_relationship")
public class PartRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rel_type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "child_part_num", nullable = false)
    private Part childPart;

    @ManyToOne
    @JoinColumn(name = "parent_part_num", nullable = false)
    private Part parentPart;
}
