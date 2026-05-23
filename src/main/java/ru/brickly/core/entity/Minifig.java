package ru.brickly.core.entity;

import jakarta.persistence.*;
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

    @OneToOne(mappedBy = "minifig", fetch = FetchType.LAZY)
    private BLMinifig blMinifig;
}
