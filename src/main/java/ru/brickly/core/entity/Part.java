package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "part")
public class Part {
    @Id
    @Column(name = "part_num")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "part_cat_id", nullable = false)
    private PartCategory category;

    @OneToOne(mappedBy = "part", fetch = FetchType.LAZY)
    private BLPart blPart;
}
