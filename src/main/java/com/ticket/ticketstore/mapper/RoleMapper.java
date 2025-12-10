package com.ticket.ticketstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ticket.ticketstore.dto.RoleDTO;
import com.ticket.ticketstore.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDto(Role role);
    List<RoleDTO> toDtos(List<Role> roles);
}
