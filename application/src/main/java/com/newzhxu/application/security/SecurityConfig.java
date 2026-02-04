package com.newzhxu.application.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newzhxu.application.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static com.newzhxu.application.common.REnum.AUTHENTICATION_FAILED;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {
    private final ObjectMapper objectMapper;

    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index.html", "/assets/**", "/login").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(configure -> {
                    configure.loginProcessingUrl("/login")
                            .successHandler((request, response, authentication) -> {
                                R<Void> ok = R.ok();
                                response.setStatus(HttpStatus.OK.value());
                                response.setContentType("application/json;charset=UTF-8");
                                response.getWriter().write(objectMapper.writeValueAsString(ok));
                            })
                            .failureHandler((request, response, exception) -> {
                                R<Void> voidR = R.of(AUTHENTICATION_FAILED);
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.getWriter().write(objectMapper.writeValueAsString(voidR));
                            })

                            .usernameParameter("username")
                            .passwordParameter("password")
                    ;
                })
                .exceptionHandling(e -> {
                    e.accessDeniedHandler((request, response, accessDeniedException) -> {
                                response.setStatus(HttpStatus.FORBIDDEN.value());
                                response.setContentType("application/json;charset=UTF-8");
                                R<Void> voidR = R.of(com.newzhxu.application.common.REnum.FORBIDDEN);
                                response.getWriter().write(objectMapper.writeValueAsString(voidR));
                            })
                    .authenticationEntryPoint((request, response, accessDeniedException) -> {
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                response.setContentType("application/json;charset=UTF-8");
                                R<Void> voidR = R.of(AUTHENTICATION_FAILED);
                                response.getWriter().write(objectMapper.writeValueAsString(voidR));
                            })
                    ;
                })
        ;
        // @formatter:on
        DefaultSecurityFilterChain build = http.build();
        return build;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // 从 Spring 内部的配置对象中获取并暴露为 Bean
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(MyUserRepo myUserRepo, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        jdbcUserDetailsManager.setJdbcTemplate(jdbcTemplate);
//        jdbcUserDetailsManager.createUser(User.withUsername("admin").password("admin").roles("USER").build());
//
//        jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
//        return jdbcUserDetailsManager;
        return new UserDetailsServiceImpl(myUserRepo, authenticationManager, passwordEncoder);
    }
}
