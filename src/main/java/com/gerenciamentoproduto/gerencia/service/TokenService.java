package com.gerenciamentoproduto.gerencia.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gerenciamentoproduto.gerencia.model.User;

@Service
public class TokenService {
	public String gerarToken(User user) {
		try {
			var _algoritmo = Algorithm.HMAC256("123456");
			return JWT.create()
					.withIssuer("API produto")
					.withClaim("id", user.getId())
					.withSubject(user.getSenha())
					.withExpiresAt(dataExpiracao())
					.sign(_algoritmo);
			
		}catch(JWTCreationException  e) {
			throw new RuntimeException("Erro ao criar Token", e);
		}
	}
	
	public String getSubject(String JwtToken) {
		try {
			var _algoritimo = Algorithm.HMAC256("123456");
		    return JWT.require(_algoritimo)
		        .withIssuer("API produto")
		        .build()
		        .verify(JwtToken)
		        .getSubject();
		}catch(JWTVerificationException e) {
			throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}
	
	public Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
