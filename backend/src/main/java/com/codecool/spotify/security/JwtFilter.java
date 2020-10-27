package com.codecool.spotify.security;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Builder
public class JwtFilter extends OncePerRequestFilter {

    public static final String TOKEN = "token";

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<Cookie> jwtToken =
                Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
                        .filter(cookie -> cookie.getName().equals(TOKEN))
                        .findFirst();
        if (jwtToken.isPresent()) {
            UsernamePasswordAuthenticationToken userToken = jwtService.parseUserFromTokenInfo(jwtToken.get().getValue());
            SecurityContextHolder.getContext().setAuthentication(userToken);

        }
        filterChain.doFilter(request, response);
    }
}
