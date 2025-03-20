package net.softplayer.e_commerce.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


@Component
public class TokenService {
	private String ISSUER;
	private SecretKey secretKey;

	public TokenService(@Value("${token.issuer}") String issuer, @Value("${token.secret}") String secret) {
		this.ISSUER = issuer;
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(Integer id) {
		return Jwts.builder()
				.issuer(ISSUER)
				.claim("id", id.toString())
				.signWith(secretKey)
				.compact();
	}
}
