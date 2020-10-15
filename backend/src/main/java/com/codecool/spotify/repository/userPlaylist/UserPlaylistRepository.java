package com.codecool.spotify.repository.userPlaylist;

import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserPlaylistRepository extends JpaRepository<UserPlaylist, Long> {

    @Query("SELECT u FROM UserPlaylist u WHERE u.spotiUser = :spotiUser")
    List<UserPlaylist> findAllBySpotiUser(SpotiUser spotiUser);

    @Query("SELECT u FROM UserPlaylist u WHERE u.title = :title AND u.spotiUser = :spotiUser")
    UserPlaylist findUserPlaylistByTitle(SpotiUser spotiUser, String title);

    @Query("SELECT u FROM UserPlaylist u WHERE u.id = :id AND u.spotiUser = :spotiUser")
    UserPlaylist findUserPlaylistById(SpotiUser spotiUser, Long id);

    @Query("DELETE FROM UserPlaylist u WHERE u.id = :id AND u.spotiUser = :spotiUser")
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteUserPlaylistById(SpotiUser spotiUser, Long id);
}
