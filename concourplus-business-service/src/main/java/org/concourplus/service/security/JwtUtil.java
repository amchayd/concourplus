package org.concourplus.service.security;

import java.util.stream.Collectors;

import org.concourplus.dto.usersetup.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;

	public UserDTO parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			UserDTO u = new UserDTO();
			u.setUsername(body.getSubject());
			u.setId(Long.parseLong((String) body.get("userId")));
			// u.setProfiles(body.get("profile"));

			return u;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String generateToken(UserDTO u) {
		Claims claims = Jwts.claims().setSubject(u.getUsername());
		claims.put("username", u.getUsername());
		claims.put("mail", u.getMail());
		claims.put("profiles", u.getProfiles().stream().map(s->s.toString()));
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	protected  String getSecretKey() {
		return secret;
	}
}
