package com.ticket.ticketstore.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticket.ticketstore.dto.*;
import com.ticket.ticketstore.mapper.UserMapper;
import com.ticket.ticketstore.model.*;
import com.ticket.ticketstore.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    

    public List<UserGetDTO> getAllUsers(){
        return userMapper.toDtos(userRepository.findAll());
    }

    public UserGetDTO getUserById(Integer id){
        return userMapper.toDto(userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    public UserGetDTO createUser(UserPostDTO dto){
        User user = userMapper.toUser(dto);

        Role role = roleRepository.findById(dto.getRoleId())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user = userRepository.save(user);
        
        return userMapper.toDto(user);
    }

    public UserGetDTO updateUser(UserPostDTO dto, Integer id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        if (!dto.getEmail().isEmpty() && dto.getEmail().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user.setEnabled(dto.getEnabled());
        user.setRole(role);

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(user);
    }

}
