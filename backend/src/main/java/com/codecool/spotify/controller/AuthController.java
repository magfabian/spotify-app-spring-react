package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUserCredentials;
import com.codecool.spotify.security.JwtService;
import com.codecool.spotify.service.SpotiUserDetailService;
import com.codecool.spotify.service.SpotiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
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

    @Autowired
    private SpotiUserService spotiUserlService;

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

            addTokenToCookie(response, token);

            addLoginToCookie(response);

            Long userId = spotiUserlService.getUserIdByEmailAddress(emailAddress);

            addIdToCookie(response, String.valueOf(userId));

            Map<Object, Object> model = new HashMap<>();
            model.put("email-address", emailAddress);
            model.put("roles", roles);
            model.put("token", token);

            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Wrong username or password!");
        }
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
                .maxAge(Duration.ofHours(24))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    private void addLoginToCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("logged_in", "true")
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
                .maxAge(Duration.ofHours(24))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    private void addIdToCookie(HttpServletResponse response, String userId) {
        ResponseCookie cookie = ResponseCookie.from("user_id", userId)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
                .maxAge(Duration.ofHours(24))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        response.addCookie(deleteUserIdFromCookie());
        response.addCookie(deleteTokenFromCookie());
        response.addCookie(deleteLoggenInFromCookie());
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
