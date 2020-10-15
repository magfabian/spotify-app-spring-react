package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import com.codecool.spotify.service.SpotiUserService;
import com.codecool.spotify.utility.PrincipalFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private SpotiUserService spotiUserService;

    @Autowired
    private SpotiUserRepository spotiUserRepository;

    @PostMapping("/signup")
    public void handleNewUser(@RequestBody SpotiUser spotiUser) {
        spotiUserService.saveNewUser(spotiUser);
    }

//    @PostMapping("/add-friend/")
//    public void handleNewFriend(@RequestBody SpotiUser friend) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//
//        spotiUserService.addNewFriend(userEmail, friend);
//    }

    @GetMapping("/me")
    public Object currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

    @GetMapping("/profile")
    public SpotiUser user(){
        String email = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
        user.setPassword("");
        return user;
    }
}
