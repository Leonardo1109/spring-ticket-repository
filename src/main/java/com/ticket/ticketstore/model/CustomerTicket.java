package com.ticket.ticketstore.model;

import java.time.LocalDateTime;

import com.ticket.ticketstore.model.id.CustomerTicketId;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerTicket {
    @EmbeddedId
    private CustomerTicketId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    @JsonBackReference("customer_ticket_ref")
    private Customer customer; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    @JsonBackReference("ticket_customer_ref")
    private Ticket ticket;

    @Column(name = "purchase_date")
    private LocalDateTime date;
}
