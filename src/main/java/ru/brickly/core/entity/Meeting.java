package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private MeetingType type;

    @Column(name = "ticket_price")
    private int ticketPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_duration")
    private Integer discountDuration;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "discount_modifier")
    private Integer discountModifier;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
