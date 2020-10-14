package com.codecool.spotify;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SpotiUserRepository spotiUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        SpotiUser spotiUser1 = SpotiUser.builder()
            .emailAddress("nembence@gmail.com")
            .fullName("Németh Bence")
            .password(passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER"))
            .userName("nembence")
            .build();

        SpotiUser spotiUser2 = SpotiUser.builder()
            .emailAddress("magfabian@gmail.com")
            .fullName("Fábián Magda")
            .password(passwordEncoder.encode("password123"))
            .roles(Arrays.asList("ROLE_USER"))
            .userName("magfabian")
            .build();

        SpotiUser spotiUser3 = SpotiUser.builder()
            .emailAddress("luca@codecool.com")
            .fullName("Siba Luca")
            .password(passwordEncoder.encode("password123"))
            .roles(Arrays.asList("ROLE_USER"))
            .userName("Luca")
            .build();

        SpotiUser spotiUser4 = SpotiUser.builder()
            .emailAddress("ligeti_tigerking_balint@gmail.com")
            .fullName("Ligeti Bálint")
            .password(passwordEncoder.encode("password987"))
            .roles(Arrays.asList("ROLE_USER"))
            .userName("tKing-Balint")
            .build();

        spotiUserRepository.saveAll(Arrays.asList(spotiUser1, spotiUser2, spotiUser3, spotiUser4));
    }
}
