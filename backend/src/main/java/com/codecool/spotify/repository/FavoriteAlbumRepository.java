package com.codecool.spotify.repository;

import com.codecool.spotify.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteAlbumRepository extends JpaRepository<Album, Long> {

    void deleteAlbumBySpotifyId(String spotifyId);
}
