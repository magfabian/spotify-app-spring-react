package com.codecool.spotify.service;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class SpotiUserService {

    @Autowired
    private SpotiUserRepository spotiUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveNewUser(SpotiUser spotiUser) {
        spotiUser.setPassword(passwordEncoder.encode(spotiUser.getPassword()));
        spotiUser.setRoles(Arrays.asList("ROLE_USER"));

        spotiUserRepository.save(spotiUser);
    }

    public Long getUserIdByEmailAddress(String emailAddress) {
        Optional<SpotiUser> spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(emailAddress);
        return spotiUser.map(SpotiUser::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
