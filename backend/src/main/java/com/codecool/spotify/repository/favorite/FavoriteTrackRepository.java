package com.codecool.spotify.repository.favorite;

import com.codecool.spotify.model.favorite.Track;
import com.codecool.spotify.model.user.SpotiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoriteTrackRepository extends JpaRepository<Track, Long> {

    @Query("SELECT t FROM Track t WHERE t.spotiUser = :spotiUser")
    List<Track> findAllBySpotiUser(SpotiUser spotiUser);

    @Query("DELETE FROM Track t WHERE t.spotifyId = :spotifyId AND t.spotiUser = :spotiUser")
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteTrackBySpotifyId(SpotiUser spotiUser, String spotifyId);
}
