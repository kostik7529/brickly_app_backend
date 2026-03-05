package ru.brickly.core.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "`user`")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Feedback> authoredFeedbacks;

    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private List<Feedback> targetedFeedbacks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;
}
