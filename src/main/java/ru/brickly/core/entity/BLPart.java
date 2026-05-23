package ru.brickly.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bl_part")
public class BLPart {
    @Id
    @OneToOne
    @JoinColumn(name = "rb_id")
    @JsonIgnore
    private Part part;

    @Column(name = "id")
    private String id;

    @Column(name = "image_url")
    private String imageUrl;
}
