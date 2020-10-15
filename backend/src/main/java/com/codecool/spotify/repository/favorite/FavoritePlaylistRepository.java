package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Playlist;
import com.codecool.spotify.model.user.SpotiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FavoritePlaylistRepository extends JpaRepository<Playlist,Long> {

    @Query("DELETE FROM Playlist p WHERE p.spotifyId = :spotifyId AND p.spotiUser = :spotiUser")
    @Transactional
    @Modifying(clearAutomatically = true)
    void deletePlaylistBySpotifyId(SpotiUser spotiUser, String spotifyId);
}
