package com.codecool.spotify.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class JwtService {

    private final long VALIDITY = 36000000;
    private final String ROLES_FIELD_NAME = "roles";

    private String secretKey = System.getProperty("SECRET_KEY");

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String emailAddress, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(emailAddress);
        claims.put(ROLES_FIELD_NAME, roles);

        Date now = new Date(System.currentTimeMillis());
        Date validity = new Date(now.getTime() + VALIDITY);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("JWT is invalid" + e);
        }
        return false;
    }

    public Authentication parseUserFromTokenInfo(String token) throws UsernameNotFoundException {

        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String emailAddress = body.getSubject();

        List<String> roles = (List<String>) body.get(ROLES_FIELD_NAME);
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(emailAddress, "", authorities);
    }
}
