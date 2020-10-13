package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaylistRepository extends JpaRepository<Playlist,Long> {

    void deletePlaylistBySpotifyId(String spotifyId);
}
