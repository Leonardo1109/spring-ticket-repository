package com.ticket.ticketstore.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.ticket.ticketstore.model.User;
import com.ticket.ticketstore.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new MyUserDetails(user);
    }
}