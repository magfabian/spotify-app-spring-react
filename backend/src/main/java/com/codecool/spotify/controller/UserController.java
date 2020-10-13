package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.service.SpotiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SpotiUserService spotiUserService;

    @PostMapping("/signup")
    public void handleNewUser(@RequestBody SpotiUser spotiUser) {
        spotiUserService.saveNewUser(spotiUser);
    }
}
