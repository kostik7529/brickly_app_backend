package ru.brickly.core.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meeting_discount")
public class MeetingDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "duration")
    private int duration;

    @Column(name = "amount")
    private int amount;

    @Column(name = "modifier")
    private int modifier;
}
