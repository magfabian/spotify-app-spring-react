package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteAlbumRepository extends JpaRepository<Album, Long> {

    void deleteAlbumBySpotifyId(String spotifyId);
}
