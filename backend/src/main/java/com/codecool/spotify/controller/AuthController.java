package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUserCredentials;
import com.codecool.spotify.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity createAuthToken(@RequestBody SpotiUserCredentials spotiUserCredentials, HttpServletResponse response) {
        try {
            String emailAddress = spotiUserCredentials.getEmailAddress();
            String password = spotiUserCredentials.getPassword();

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailAddress, password));

            List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

            String token = jwtService.createToken(emailAddress, roles);
            ResponseCookie cookie = addTokenToCookie(token);

            Map<Object, Object> model = new HashMap<>();
            model.put("email-address", emailAddress);
            model.put("roles", roles);
            model.put("token", token);

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Wrong username or password!");
        }
    }

    private ResponseCookie addTokenToCookie(String token) {
        return ResponseCookie
            .from("token", token)
            .maxAge(Duration.ofHours(18))
            .path("/")
            .httpOnly(true)
            .secure(false)
            .build();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        ResponseCookie cookie = ResponseCookie
            .from("token", "")
            .maxAge(0)
            .path("/")
            .httpOnly(true)
            .secure(false)
            .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("");
    }

    public Cookie deleteUserIdFromCookie() {
        Cookie cookie = new Cookie("user_id", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    public Cookie deleteLoggenInFromCookie() {
        Cookie cookie = new Cookie("logged_in", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    public Cookie deleteTokenFromCookie() {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }
}
