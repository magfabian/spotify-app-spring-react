package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Artist;
import com.codecool.spotify.model.user.SpotiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FavoriteArtistRepository extends JpaRepository<Artist,Long> {

    @Query("DELETE FROM Artist a WHERE a.spotifyId = :spotifyId AND a.spotiUser = :spotiUser")
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteArtistBySpotifyId(SpotiUser spotiUser, String spotifyId);
}
