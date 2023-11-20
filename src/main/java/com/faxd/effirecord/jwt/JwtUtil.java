package com.faxd.effirecord.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig jwtConfig;


    public String createJWT(Long employeeId){
        return createJWT(employeeId, null);
    }

    public String createJWT (Long employeeId, Long ttlMillis){
        if(ttlMillis==null){
            ttlMillis = jwtConfig.getJwtTtl();
        }
        Date expireDate = new Date(System.currentTimeMillis() + ttlMillis);
        return Jwts.builder()
                .setSubject(employeeId.toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public Long parseJWT(String token) {
        String employeeId = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.parseLong(employeeId);
    }
}