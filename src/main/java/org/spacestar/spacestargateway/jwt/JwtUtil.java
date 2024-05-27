package org.spacestar.spacestargateway.jwt;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {

	private SecretKey secretKey;

	public String getUuid(String token){
		return parseClaims(token).get("uuid", String.class);
	}

	private Claims parseClaims(String accessToken){

		try{
			return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e){

		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){

		}
	}
}
