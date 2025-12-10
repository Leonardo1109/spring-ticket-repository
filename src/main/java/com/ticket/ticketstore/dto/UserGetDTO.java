package com.ticket.ticketstore.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetDTO {
    private Integer id;
    private String userName;
    private String email;
    private Boolean enabled;
    private RoleDTO role;
}
