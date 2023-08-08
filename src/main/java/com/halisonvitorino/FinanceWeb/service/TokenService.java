package com.halisonvitorino.FinanceWeb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.halisonvitorino.FinanceWeb.models.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        return JWT.create()
                .withIssuer("Transacao")
                .withSubject(usuario.getUsername())
                //.withClaim("id", usuario.getUsername())
                .withExpiresAt(LocalDateTime.now()
                .plusMinutes(10)
                .toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secreto"));
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secreto");
            return JWT.require(algorithm)
                    .withIssuer("Transacao")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
}
