package com.ticket.ticketstore.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketGetDTO {
    private Integer id;

    private float price;

    private String seat;

    private List<CustomerDTO> customers;

    private EventGetDTO event;

}
