package com.codecool.spotify.repository;

import com.codecool.spotify.model.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {

    UserPlaylist findUserPlaylistByTitle(String title);
}
