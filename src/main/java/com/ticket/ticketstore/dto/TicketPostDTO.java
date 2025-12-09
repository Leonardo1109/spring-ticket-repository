package com.ticket.ticketstore.dto;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPostDTO {
    @NotNull
    private float price;

    @NotNull
    @NotBlank(message = "Ingrese un valor valido")
    @Size(min = 5, max = 250, message = "Ingrese un valor de 5 a 250 caracteres")
    private String seat;

    @NotNull
    private List<Integer> customerIds;

    @NotNull
    private Integer eventId;
}
