package com.codecool.spotify.repository;

import com.codecool.spotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaylistRepository extends JpaRepository<Playlist,Long> {

    void deletePlaylistBySpotifyId(String spotifyId);
}
