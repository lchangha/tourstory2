package io.traveler.travel.global.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

@Component
public class JwtTokenProvideFilter extends OncePerRequestFilter {

    private final String JWT_SECRET_KEY;
    private final String JWT_SECRET_DEFAULT_VALUE;
    private final String JWT_HEADER;

    public JwtTokenProvideFilter(@Value("${jwt-secret-key}") String JWT_SECRET_KEY,
                                 @Value("${jwt-secret-default-value}") String JWT_SECRET_DEFAULT_VALUE,
                                 @Value("${jwt-header}") String JWT_HEADER) {
        this.JWT_SECRET_KEY = JWT_SECRET_KEY;
        this.JWT_SECRET_DEFAULT_VALUE = JWT_SECRET_DEFAULT_VALUE;
        this.JWT_HEADER = JWT_HEADER;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

          if (authentication != null) {
              String secret = (JWT_SECRET_KEY != null && !JWT_SECRET_KEY.isEmpty())
                      ? JWT_SECRET_KEY
                      : JWT_SECRET_DEFAULT_VALUE;
              SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

              String jwt = Jwts.builder()
                      .setIssuer("travel")
                      .setSubject("jwt-Token")
                      .claim("username", authentication.getName())
                      .claim("authorities", authentication.getAuthorities().stream().map(
                              GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                      .setIssuedAt(new Date())
                      .setExpiration(new Date((new Date()).getTime() + 30000000))
                      .signWith(secretKey).compact();
              response.setHeader(JWT_HEADER, jwt);
          }
          filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !("/api/login".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod()));
    }
}



