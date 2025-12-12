package com.ticket.ticketstore.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ticket.ticketstore.dto.LoginRequest;
import com.ticket.ticketstore.security.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager; // autenticacion por defecto
    private final MyUserDetailsService userDetailsService; // servicio del usuario
    private final JwtService jwtService; // servicio jwt

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest dto) {

        authManager.authenticate( // compara las contraseñas
            new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
            )
        );

        // si no hay problemas carga el usuario
        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        // genera token
        return jwtService.generateToken(user);
    }

}
