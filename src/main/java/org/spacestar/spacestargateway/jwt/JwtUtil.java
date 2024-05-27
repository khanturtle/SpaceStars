package org.spacestar.spacestargateway.jwt;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private SecretKey secretKey;


	// jwt 에서 클레임 추출
	private Claims parseClaims(String accessToken){

		try{
			return Jwts.parser()
					.verifyWith(secretKey)
					.build()
					.parseSignedClaims(accessToken)
					.getPayload();
		} catch (ExpiredJwtException e){
			throw new IllegalStateException("만료된 토큰입니다.");
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
			throw new IllegalStateException("유효하지 않은 토큰입니다.");
		}
	}

	// 토큰을 파싱하여 클레임 객체 반환 -> 반환된 클레임 객체에서 uuid 추출
	public String getUuid(String token){
		return parseClaims(token).get("uuid", String.class);
	}

	// role 클레임 추출
	public String getRole(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
	}

	// 토큰 만료 여부 확인
	public Boolean isExpired(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}
}
