package sleepapp.java.base.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtService {
	
	public String createJwt(String username) {
		
		Instant now = Instant.now();
		
		byte[] secret = "RraIY0negneEQzv3XO6kwjN4XVtsul1A".getBytes();
		
		String jwt = Jwts.builder()
				.setSubject("oba")
				.setIssuer(username)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plusSeconds(1200)))
				.signWith(Keys.hmacShaKeyFor(secret))
				.compact();
		
		System.out.println(jwt + "   " + Keys.hmacShaKeyFor(secret).toString());
		
		return jwt;
	}
	
	public void parseKey(String jwt) {
		
		byte[] secret = "RraIY0negneEQzv3XO6kwjN4XVtsul1A".getBytes();
		
		try {
			
			Jws<Claims> result = Jwts.parserBuilder()
					 .setSigningKey(Keys.hmacShaKeyFor(secret))
			         .build()
			         .parseClaimsJws(jwt);
			
		}catch (SignatureException e) {
			
			throw new SignatureException("Token Inválido");
		}
		
		
		
	}
	
	public void test() {
		
		
	}
}
