package com.codecool.spotify.repository;

import com.codecool.spotify.model.UserPlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlaylistTrackRepository extends JpaRepository<UserPlaylistTrack,Long> {
}
