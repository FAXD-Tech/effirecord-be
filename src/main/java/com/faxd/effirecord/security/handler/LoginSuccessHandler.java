package com.faxd.effirecord.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faxd.effirecord.auth.EffiRecordUserDetails;
import com.faxd.effirecord.constant.R;
import com.faxd.effirecord.jwt.JwtUtil;
import com.faxd.effirecord.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    private final RedisCacheService redisCacheService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        EffiRecordUserDetails employee = (EffiRecordUserDetails)authentication.getPrincipal();
        employee.clearPassword();
        Long employeeId = employee.getId();
        String jwtToken = jwtUtil.createJWT(employeeId);
        redisCacheService.setCacheObject(R.Redis.LONGIN_KEY_PREFIX + employeeId, employee);

        Map<String, Object> employeeInfo = new HashMap<>();
        employeeInfo.put("employeeId", employeeId);
        employeeInfo.put("authorities", authorities);
        ObjectMapper objectMapper = new ObjectMapper();
        String userInfoJson = objectMapper.writeValueAsString(employeeInfo);

        response.addHeader(HttpHeaders.AUTHORIZATION, R.JWT.PREFIX + jwtToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(userInfoJson);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
