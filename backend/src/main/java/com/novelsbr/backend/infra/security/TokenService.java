package com.novelsbr.backend.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.novelsbr.backend.domain.entities.Author;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Author author) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("novelsbr")
					.withSubject(author.getUsername())
					.withExpiresAt(genExperitionDate())
					.sign(algorithm);
			
			return token;
			
		} catch(JWTCreationException e) {
			throw new RuntimeException("Error to generate Token: " + e);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("novelsbr")
					.build()
					.verify(token)
					.getSubject();
		} catch(JWTVerificationException e) {
			return "Error to verificate Token " + e;
		}
	}
	
	public Instant genExperitionDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
