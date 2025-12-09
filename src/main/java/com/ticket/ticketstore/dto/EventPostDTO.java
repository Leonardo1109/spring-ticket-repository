package com.ticket.ticketstore.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventPostDTO {

    @NotNull
    @NotBlank(message = "Ingrese un valor valido")
    @Size(min = 5, max = 200, message = "Ingrese un valor de 5 a 200 caracteres")
    private String title;

    @FutureOrPresent(message = "La fecha debe ser presente o futura")
    private LocalDate date;

    @NotNull
    private Integer venueId;

    @NotNull
    private List<Integer> artistIds;
}
