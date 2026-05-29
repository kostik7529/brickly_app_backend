package ru.brickly.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private OffsetDateTime date;

    @Column(name = "title")
    private String title;

    @Column(name = "announce_date")
    private OffsetDateTime announceDate;

    @Column(name = "duration")
    private Integer duration;

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

    @Column(name = "preview_image_path")
    private String previewImagePath;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
