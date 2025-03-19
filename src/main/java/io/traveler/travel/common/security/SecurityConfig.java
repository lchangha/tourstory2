package io.traveler.travel.common.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.cors.*;

import java.util.*;

@Configuration
public class SecurityConfig {

    private final String BASE_URL;
    private final String JWT_HEADER;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvideFilter jwtTokenProvideFilter;
    private final JwtTokenValidateFilter jwtTokenValidateFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(@Value("${base-url}") String BASE_URL,
                          @Value("${jwt-header}") String JWT_HEADER,
                          UserDetailsServiceImpl userDetailsService,
                          JwtTokenProvideFilter jwtTokenProvideFilter,
                          JwtTokenValidateFilter jwtTokenValidateFilter,
                          PasswordEncoder passwordEncoder) {
        this.BASE_URL = BASE_URL;
        this.JWT_HEADER = JWT_HEADER;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvideFilter = jwtTokenProvideFilter;
        this.jwtTokenValidateFilter = jwtTokenValidateFilter;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TODO: 프론트에서 로그인요청을 json 으로 줄건지 form-data 로 줄건지에 따라 UsernamePasswordAuthenticationFilter를 커스텀 하거나 살려두던가
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtTokenValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtTokenProvideFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/**/private/**").authenticated()
                        .requestMatchers(HttpMethod.POST).authenticated()
                        .requestMatchers(HttpMethod.PUT).authenticated()
                        .requestMatchers(HttpMethod.DELETE).authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(BASE_URL));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of(JWT_HEADER));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
