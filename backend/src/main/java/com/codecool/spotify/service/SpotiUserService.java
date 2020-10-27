package com.codecool.spotify.service;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

//    public void addNewFriend(String emailAddress, SpotiUser friend) {
//        SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(emailAddress)
//            .orElseThrow(() -> new UsernameNotFoundException("Email address" + emailAddress + "not found."));
//
//        List<Friend> friendList = user.getFriends();
//        friendList.add(Friend.builder().selfId(friend.getId()).follower(user).build());
//
//        user.setFriends(friendList);
//        spotiUserRepository.save(user);
//    }
}
