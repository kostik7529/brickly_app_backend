package ru.brickly.core.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meeting_type")
public class MeetingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;
}
