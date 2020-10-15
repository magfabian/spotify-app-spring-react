package com.codecool.spotify.controller;

import com.codecool.spotify.model.user.SpotiUser;import com.codecool.spotify.service.SpotiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private SpotiUserService spotiUserService;

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
}
