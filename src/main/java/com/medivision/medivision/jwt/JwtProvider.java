package com.medivision.medivision.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;
    
    //jwt 생성 메소드
    public String create(int userCode){

        Date expiredDate = Date.from(Instant.now().plus(2, ChronoUnit.HOURS));

        String userCodeVarchar = String.valueOf(userCode);

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .setSubject(userCodeVarchar).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();

        return jwt;
    }
    
    //jwt 검증 하는 메소드
    public String validate(String jwt){

        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey) //파싱 열고 시크릿키 넣어주고 jwt토큰 넣어준 결과값을 반환
                    .parseClaimsJws(jwt).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return claims.getSubject();
    }

}
