package com.ticket.ticketstore.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // traer la clave secreta para firmar y verificar tokens
    @Value("${security.jwt.secret}")
    private String secretKey;

    // trae la duracion del token 
    @Value("${security.jwt.expiration}")
    private long jwtExpiration; // en ms, ejemplo: 3600000 = 1 hora

    // Convierte la clave secreta compatible con HS256
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // extraer el usuario 
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /*
        Aquí se define:
        * sub (subject) → username
        * fecha de emisión
        * fecha de expiración
        * firma HS256 con tu clave secreta
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // verifica que el token sea valido
    // Verificar que el token pertenezca al usuario que estás autenticando.
    // Verificar que el token no haya expirado.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}