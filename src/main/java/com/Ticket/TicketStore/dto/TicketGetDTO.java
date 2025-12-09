package com.Ticket.TicketStore.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
