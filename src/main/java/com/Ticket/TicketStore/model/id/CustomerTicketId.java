package com.Ticket.TicketStore.model.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CustomerTicketId implements Serializable{

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "ticket_id")
    private Integer ticketId;
}
