package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteArtistRepository extends JpaRepository<Artist,Long> {

    void deleteArtistBySpotifyId(String spotifyId);
}
