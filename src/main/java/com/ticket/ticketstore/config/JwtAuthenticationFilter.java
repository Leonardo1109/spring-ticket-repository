package com.ticket.ticketstore.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ticket.ticketstore.security.JwtService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
// Filtro de JWT para revisar si la petición trae un token y validarlo
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) 
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // lee el header del token

        // si no tiene deja pasar la peticion sin autenticacion
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae el token (beareer <token>) -> quita "bearer "
        final String jwt = authHeader.substring(7);
        // obtiene username
        final String username = jwtService.extractUsername(jwt);

        //evitar redundancia en el contexto
        if (username != null && 
            SecurityContextHolder.getContext().getAuthentication() == null) {

            // cargar al usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // vaida el token y que pertenezca al usuario
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Autentica el usuario en spring sabiendo sus detalles y su rol ademas de permisos
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
