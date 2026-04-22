package com.jsp.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	// Ensure your secret is at least 256 bits (32 characters) long for HS256
	private final String SECRET = "my-super-secret-key-that-is-long-enough-123456789!@#";

	// Best practice: Explicitly state the character set when getting bytes
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fixed date calculation
				.signWith(key) // The algorithm (HS256) is now automatically inferred from the key length
				.compact();
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String username, UserDetails userDetail, String token) {
		return username.equals(userDetail.getUsername()) && isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
}