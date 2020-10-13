package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUserCredentials;
import com.codecool.spotify.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity createAuthToken(@RequestBody SpotiUserCredentials spotiUserCredentials) {
        try {
            String emailAddress = spotiUserCredentials.getEmailAddress();
            String password = spotiUserCredentials.getPassword();

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailAddress, password));

            List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

            String token = jwtService.createToken(emailAddress, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email-address", emailAddress);
            model.put("roles", roles);
            model.put("token", token);

            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }
}
