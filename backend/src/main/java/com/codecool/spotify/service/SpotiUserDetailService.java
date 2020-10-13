package com.codecool.spotify.service;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SpotiUserDetailService implements UserDetailsService {

    @Autowired
    private SpotiUserRepository spotiUserRepository;


    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(emailAddress)
            .orElseThrow(() -> new UsernameNotFoundException("Email address: " + emailAddress + " not found"));

        return new User(
            user.getEmailAddress(),
            user.getPassword(),
            user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
