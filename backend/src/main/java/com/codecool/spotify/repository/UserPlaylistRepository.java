package com.codecool.spotify.repository;

import com.codecool.spotify.model.UserPlaylist;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.desktop.UserSessionEvent;

public interface UserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {

    UserPlaylist findUserPlaylistByTitle(String title);
}
