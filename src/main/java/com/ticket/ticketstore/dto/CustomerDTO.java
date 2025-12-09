package com.ticket.ticketstore.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    
    private Integer id;

    @NotNull
    @NotBlank(message = "Ingrese un valor valido")
    @Size(min = 5, max = 150, message = "Ingrese un valor de 5 a 150 caracteres")
    private String name;

    @Email(message = "Ingrese un valor valido")
    private String email;

}
