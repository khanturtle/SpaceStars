package com.spacestar.back.member.jwt;


import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JWTUtil {

    private SecretKey secretKey;


    public JWTUtil(@Value("${JWT.SECRET_KEY}")String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUuid(String token) {

        return parseClaims(token).get("uuid", String.class);

    }

    //토큰 추출
    private Claims parseClaims(String accessToken) {
//        try {
//            return Jwts.parser()
//                    .setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e){
//            return e.getClaims();
//        }
        //gateway에서 토큰을 검증하기 때문에 gateway가 적용되면 지울 예정
        try{
            return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e){
            throw new GlobalException(ResponseStatus.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new GlobalException(ResponseStatus.TOKEN_NOT_VALID);
        }
    }


    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String uuid, String role, @Value("${EXPIRATION_TIME}")Long expiredMs) {

        return Jwts.builder()
                .claim("uuid", uuid)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String getHeader(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            //"Bearer " 부분 제거
            return headerValue.substring(7).trim();
        }
        throw new GlobalException(ResponseStatus.TOKEN_NOT_VALID);
    }
}
