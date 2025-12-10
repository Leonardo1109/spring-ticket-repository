package com.ticket.ticketstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.ticketstore.dto.RoleDTO;
import com.ticket.ticketstore.mapper.RoleMapper;
import com.ticket.ticketstore.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles(){
        return roleMapper.toDtos(roleRepository.findAll());
    }

    public RoleDTO getRoleById(Integer id){
        return roleMapper.toDto(roleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado")));
    }
}
