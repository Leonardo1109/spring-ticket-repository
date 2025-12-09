package com.Ticket.TicketStore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venues")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private Integer capacity;

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    @JsonManagedReference("venue_event_ref")
    private List<Event> events;
}
