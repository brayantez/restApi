package com.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/v1/article/**"
    };

    private final AuthenticationProvider authenticationProvider;


    public WebSecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
            req.requestMatchers(WHITE_LIST_URL)
                    .permitAll()
//                    .requestMatchers("/api/v1/article/**").hasAnyRole("ADMIN")
//                    .requestMatchers(GET, "/api/v1/article/**").hasAnyAuthority("ADMIN_READ")
//                    .requestMatchers(POST, "/api/v1/article/**").hasAnyAuthority("ADMIN_CREATE")
//                    .requestMatchers(PUT, "/api/v1/article/**").hasAnyAuthority("ADMIN_UPDATE")
//                    .requestMatchers(DELETE, "/api/v1/article/**").hasAnyAuthority("ADMIN_DELETE")
            .anyRequest()
            .authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider);
//            .addFilterBefore(CustomFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
