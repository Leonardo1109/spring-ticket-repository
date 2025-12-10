package com.ticket.ticketstore.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPostDTO {

    @NotBlank
    @Size(min = 5, max = 200, message = "El nombre de usuario debe tener una longitud entre 5 y 200 caracteres")
    private String userName;

    @Email(message = "Ingrese un correo valido")
    private String email;

    @NotBlank
    @Size(min = 5, max = 200, message = "La contraseña debe tener una longitud entre 5 y 200 caracteres")
    private String password;
    
    @NotNull
    private Boolean enabled;

    @NotNull
    private Integer roleId;
}
