package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.service.SpotiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SpotiUserService spotiUserService;

    @PostMapping("/signup")
    public void handleNewUser(@RequestBody SpotiUser spotiUser) {
        spotiUserService.saveNewUser(spotiUser);
    }

    @GetMapping("/me")
    public Object currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }
}
