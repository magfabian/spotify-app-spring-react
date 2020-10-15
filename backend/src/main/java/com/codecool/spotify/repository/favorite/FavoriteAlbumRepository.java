package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Album;
import com.codecool.spotify.model.user.SpotiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteAlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE a.spotiUser = :spotiUser")
    List<Album> findAllBySpotiUser(SpotiUser spotiUser);

    @Query("DELETE FROM Album a WHERE a.spotifyId = :spotifyId AND a.spotiUser = :spotiUser")
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteAlbumBySpotifyId(SpotiUser spotiUser, String spotifyId);
}
