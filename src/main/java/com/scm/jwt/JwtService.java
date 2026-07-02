package com.scm.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	
	
	private	static final String SECRET = 
			"z045efg*(habc#$%^&dmnopijklt67810)uvwx239qrs!";
	
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}
	
	
	public String generateToken(UserDetails user) {
		
		String role = user.getAuthorities()
				.stream()
				.findFirst()
				.get()
				.getAuthority();
		
		
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("role", role)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+3600000))
				.signWith(getKey())
				.compact();
	}
	
	
	public String extractUsername(String token) {
		
		return Jwts.parser()
				.verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                //.get("role",String.class);
          .getSubject();
		
	}
	
	
	public Date extractExpiration(String token) {
		
		return Jwts.parser()
				.verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
		
	}

	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername())
				&& !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	
}
