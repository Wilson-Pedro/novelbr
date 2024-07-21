package com.wilsonpedro.novelbr.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wilsonpedro.novelbr.entities.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("novelbr")
					.withSubject(user.getPseudonym())
					.withExpiresAt(genExporationDate())
					.sign(algorithm);
			
			return token;
			
		} catch(JWTCreationException ex) {
			throw new RuntimeException("Error while generating token. " + ex);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("novelbr")
					.build()
					.verify(token)
					.getSubject();
			
		} catch(JWTVerificationException ex) {
			return "";
		}
	}
	
	
	private Instant genExporationDate() {
		return LocalDateTime.now().plusHours(3L).toInstant(ZoneOffset.of("-03:00"));
	}

}
