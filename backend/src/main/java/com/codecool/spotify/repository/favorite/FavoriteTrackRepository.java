package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTrackRepository extends JpaRepository<Track, Long> {

    void deleteTrackBySpotifyId(String spotifyId);
}
