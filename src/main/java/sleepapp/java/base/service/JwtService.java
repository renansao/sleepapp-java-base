package sleepapp.java.base.service;

import java.time.Instant;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtService {
	
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "RraIY0negneEQzv3XO6kwjN4XVtsul1A";
	
	public String createJwt(String username) {
		
		Instant now = Instant.now();
		
		byte[] secret = "RraIY0negneEQzv3XO6kwjN4XVtsul1A".getBytes();
		
		String jwt = Jwts.builder()
				.setSubject(username)
				.setIssuer("sleepapp-base")
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

	public String retrieveSub(HttpServletRequest request) {
		return validateToken(request).getSubject();
	}
	
	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
}
