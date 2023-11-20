package com.faxd.effirecord.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.crypto.SecretKey;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtConfig jwtConfig;

    @Test
    void createAndParseJWTTest(){
        SecretKey secretKey = Keys.hmacShaKeyFor("secretKey-d5689193-8bc1-419f-a062-9779ee25ce03".getBytes());
        JwtUtil jwtUtil = new JwtUtil(jwtConfig);

        Long employeeId = 20L;
        String jwt = jwtUtil.createJWT(employeeId);
        System.out.println(jwt);
        Long parsedId = jwtUtil.parseJWT(jwt);
        assertEquals(employeeId, parsedId);
    }
}
