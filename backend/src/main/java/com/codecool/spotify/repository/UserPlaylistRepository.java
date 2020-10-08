package com.codecool.spotify.repository;

import com.codecool.spotify.model.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {

    UserPlaylist findUserPlaylistByTitle(String title);

    UserPlaylist findUserPlaylistById(Long id);

    void deleteUserPlaylistById(Long id);

    @Transactional
    @Query("UPDATE UserPlaylist U SET U.title = :newTitle WHERE U.id = :id")
    @Modifying(clearAutomatically = true)
    UserPlaylist updateUserPlaylistTitle(@Param("id") Long id, @Param("newTitle") String newTitle);
}
