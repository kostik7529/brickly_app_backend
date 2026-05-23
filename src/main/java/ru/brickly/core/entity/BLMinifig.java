package ru.brickly.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bl_minifig")
public class BLMinifig {
    @Id
    @OneToOne
    @JoinColumn(name = "rb_id")
    @JsonIgnore
    private Minifig minifig;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "url")
    private String url;
}
