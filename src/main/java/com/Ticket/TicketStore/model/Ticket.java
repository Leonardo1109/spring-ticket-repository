package com.Ticket.TicketStore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float price;

    private String seat;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY)
    @JsonManagedReference("ticket_customer_ref")
    private List<CustomerTicket> customerTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonBackReference("event_ticket_ref")
    private Event event;
}
