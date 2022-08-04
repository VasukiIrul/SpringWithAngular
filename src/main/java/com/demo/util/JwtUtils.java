package com.demo.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.demo.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String secret = "this_is_secret";

	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		// claims

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		Claims claims = Jwts.claims().setIssuer(user.getId().toString())

				.setIssuedAt(issuedAt).setExpiration(expiryAt);

		claims.put("type", user.getUserType());
		claims.put("name", user.getName());
		claims.put("email_id", user.getEmailId());

		// generate jwt using claims
		// return Jwts.builder().setClaims(claims).compact();

		return Jwts.builder().signWith(SignatureAlgorithm.HS512, secret).setClaims(claims).compact();

	}

	public void verify(String authorization) throws Exception {
		// System.out.println("JWT values are "+authorization);
		try {

			Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);

		} catch (Exception e) {
			
			throw new Exception();
		}
	}

}
