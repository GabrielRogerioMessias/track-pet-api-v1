package com.unifio.tcc.track_pet.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.unifio.tcc.track_pet.domain.usuario.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.security}")
    private String secretKey;

    public Token generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Token token = new Token();
            token.setEmail(usuario.getEmail());
            token.setAuthenticated(true);
            token.setCreated(new Date());
            token.setExpiration(Date.from(this.generateExpirationDate()));
            token.setToken(JWT.create()
                    .withIssuer("finsyn-api")
                    .withSubject(token.getEmail())
                    .withExpiresAt(token.getExpiration())
                    .sign(algorithm));
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(7200);
    }

    public String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("finsyn-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String recoveryToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
}
