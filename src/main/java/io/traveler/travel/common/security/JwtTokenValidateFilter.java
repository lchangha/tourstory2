package io.traveler.travel.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.*;
@Component
public class JwtTokenValidateFilter extends OncePerRequestFilter {

    private final String JWT_SECRET_KEY;
    private final String JWT_SECRET_DEFAULT_VALUE;
    private final String JWT_HEADER;

    public JwtTokenValidateFilter(@Value("${jwt-secret-key}") String JWT_SECRET_KEY,
                                  @Value("${jwt-secret-default-value}") String JWT_SECRET_DEFAULT_VALUE,
                                  @Value("${jwt-header}") String JWT_HEADER) {
        this.JWT_SECRET_KEY = JWT_SECRET_KEY;
        this.JWT_SECRET_DEFAULT_VALUE = JWT_SECRET_DEFAULT_VALUE;
        this.JWT_HEADER = JWT_HEADER;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);
        if(jwt != null) {
            try {
                String secret = (JWT_SECRET_KEY != null && !JWT_SECRET_KEY.isEmpty())
                        ? JWT_SECRET_KEY
                        : JWT_SECRET_DEFAULT_VALUE;

                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                if (secretKey != null) {
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();

                    String username = String.valueOf(claims.get("username"));
                    String authorities = String.valueOf(claims.get("authorities"));

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return ("/api/login".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod()));
    }

}