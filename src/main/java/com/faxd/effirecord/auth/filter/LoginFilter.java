package com.faxd.effirecord.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faxd.effirecord.dto.employee.EmployeeLoginDto;
import com.faxd.effirecord.exception.UsernameAndPasswordException;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private  AuthenticationManager authenticationManager;
    private static final String LOGIN_URL = "/employee/login";

    public LoginFilter(AuthenticationManager authenticationManager){
        super.setFilterProcessesUrl(LOGIN_URL);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        EmployeeLoginDto loginRequest = new ObjectMapper()
                .readValue(request.getInputStream(), EmployeeLoginDto.class);

        String username = (loginRequest.getPhone() != null) ? loginRequest.getPhone() : loginRequest.getName();

        if (username == null || username.trim().isEmpty()) {
            throw new UsernameAndPasswordException("Username is empty");
        }
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(username, loginRequest.getPassword());

        return this.authenticationManager.authenticate(authentication);
    }

}
