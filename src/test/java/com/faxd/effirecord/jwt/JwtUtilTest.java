package com.faxd.effirecord.jwt;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.crypto.SecretKey;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void createAndParseJWTTest(){
        Long employeeId = 20L;
        String jwt = jwtUtil.createJWT(employeeId);
        System.out.println(jwt);
        Long parsedId = jwtUtil.parseJWT(jwt);
        assertEquals(employeeId, parsedId);
    }
}
