package io.traveler.travel.user.controller;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.service.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import javax.crypto.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final String secret;
    private final SecretKey secretKey;
    private final String jwtHeader;


    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          @Value("${jwt-secret-key}") String jwtSecretKey,
                          @Value("${jwt-secret-default-value}") String jwtSecretDefaultValue,
                          @Value("${jwt-header}") String jwtHeader) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        secret = (jwtSecretKey != null && !jwtSecretKey.isEmpty())
                ? jwtSecretKey
                : jwtSecretDefaultValue;
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtHeader = jwtHeader;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = Jwts.builder()
                .setIssuer("travel")
                .setSubject("jwt-Token")
                .claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(secretKey).compact();

        HttpHeaders headers = new HttpHeaders();
        headers.set(jwtHeader, jwt);

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("signup")
    public void createUser(@RequestBody @Valid SignUpRequest SignUpRequest) {
        userService.registerUser(SignUpRequest);
    }
}
