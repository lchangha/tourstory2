package io.traveler.travel.global.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.password.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.factory.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.password.*;
import org.springframework.security.web.authentication.www.*;
import org.springframework.web.cors.*;

import java.util.*;

@Configuration
public class SecurityConfig {

    private final String BASE_URL;
    private final String JWT_HEADER;
    private final JpaUserDetailsService userDetailsService;
    private final JwtTokenValidateFilter jwtTokenValidateFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(@Value("${base-url}") String BASE_URL,
                          @Value("${jwt-header}") String JWT_HEADER,
                          JpaUserDetailsService userDetailsService,
                          JwtTokenValidateFilter jwtTokenValidateFilter,
                          PasswordEncoder passwordEncoder) {
        this.BASE_URL = BASE_URL;
        this.JWT_HEADER = JWT_HEADER;
        this.userDetailsService = userDetailsService;
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
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenValidateFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/**/private/**").authenticated()
                        .requestMatchers(HttpMethod.POST).authenticated()
                        .requestMatchers(HttpMethod.PUT).authenticated()
                        .requestMatchers(HttpMethod.DELETE).authenticated()
                        .requestMatchers("api/**/public/**").permitAll()
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
