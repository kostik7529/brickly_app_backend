package ru.brickly.core.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private User target;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "rate")
    private int rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "moderation")
    private String moderation;
}
