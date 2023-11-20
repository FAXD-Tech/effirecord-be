package com.faxd.effirecord.security.config;


import com.faxd.effirecord.auth.UserDetailsServiceImpl;
import com.faxd.effirecord.auth.filter.JwtAuthenticationTokenFilter;
import com.faxd.effirecord.auth.filter.LoginFilter;
import com.faxd.effirecord.exception.MyAccessDeniedException;
import com.faxd.effirecord.jwt.JwtConfig;
import com.faxd.effirecord.jwt.JwtUtil;
import com.faxd.effirecord.security.handler.LoginFailureHandler;
import com.faxd.effirecord.security.handler.LoginSuccessHandler;
import com.faxd.effirecord.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RedisCacheService redisCacheService;
    private final JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests.antMatchers("/employee/login","/employee/logout","/employee").permitAll()
                )
                .formLogin(formLogin -> formLogin.
                        loginProcessingUrl("/employee/login").permitAll())
                .addFilterBefore(new JwtAuthenticationTokenFilter(new JwtUtil(jwtConfig), redisCacheService), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e.accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setContentType("text/html;charset=UTF-8");
                        if (accessDeniedException instanceof MyAccessDeniedException) {
                            response.getWriter().write("CANNOT BE ACCESSIBLE:" + accessDeniedException.getMessage());
                        } else {
                            response.getWriter().write("ERROR:" + accessDeniedException.getMessage());
                        }
                    }
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .logout(logout -> logout.invalidateHttpSession(true));

        return http.build();
    }

    /**
     * PasswordEncoder:加密编码
     * 实际开发中开发环境一般是明文加密 在生产环境中是密文加密 也就可以可以配置多种加密方式
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationManager());
        loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler(new JwtUtil(jwtConfig),redisCacheService));
        loginFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        return loginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }
}
