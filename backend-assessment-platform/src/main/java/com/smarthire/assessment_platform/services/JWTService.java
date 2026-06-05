package com.smarthire.assessment_platform.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	@Value("${jwt_secret_key}")
	private String secretKey;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.header().empty().add("typ","JWT")
				.and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()*1000*60*5))
				.signWith(getSigningKey())
				.compact();
	}
	
	public boolean validateToken(String token) {
		Date expirationDate = extractAllClaims(token).getExpiration();
		return !(expirationDate.before(new Date()));
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
