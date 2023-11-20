package com.faxd.effirecord.auth.filter;

import com.faxd.effirecord.auth.EffiRecordUserDetails;
import com.faxd.effirecord.constant.R;
import com.faxd.effirecord.jwt.JwtUtil;
import com.faxd.effirecord.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final RedisCacheService redisCacheService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(R.JWT.PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token =authHeader.replace(R.JWT.PREFIX, "");
        Long employeeId;
        try{
            employeeId = jwtUtil.parseJWT(token);
        }catch (Exception e){
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("invalid token");
            return;
        }
        String redisKey = R.Redis.LONGIN_KEY_PREFIX + employeeId;
        EffiRecordUserDetails uerDetails = redisCacheService.getCacheObject(redisKey);
        if(Objects.isNull(uerDetails)){
            throw new RuntimeException("user does not login");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                uerDetails,
                null,
                uerDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}

