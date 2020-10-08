package com.codecool.spotify.repository;

import com.codecool.spotify.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTrackRepository extends JpaRepository<Track, Long> {

    void deleteTrackBySpotifyId(String spotifyId);
}
