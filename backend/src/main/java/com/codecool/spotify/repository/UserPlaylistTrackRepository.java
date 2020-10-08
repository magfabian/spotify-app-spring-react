package com.codecool.spotify.repository;

import com.codecool.spotify.model.UserPlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserPlaylistTrackRepository extends JpaRepository<UserPlaylistTrack,Long> {

    void deleteUserPlaylistTrackBySpotifyId(String spotifyId);

    @Transactional
    @Query(
        "DELETE FROM UserPlaylistTrack U " +
            "WHERE U.userPlaylist.id = :playlistId " +
            "AND U.spotifyId = :spotifyId")
    @Modifying(clearAutomatically = true)
    void deleteTrackFromPlaylist(@Param("playlistId") Long playlistId, @Param("spotifyId") String spotifyId);
}
