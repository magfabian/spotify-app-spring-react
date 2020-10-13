package com.codecool.spotify.repository;

import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistRepository;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistTrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UserPlaylistRepositoryTest {

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;

    @Autowired
    private UserPlaylistTrackRepository userPlaylistTrackRepository;

    @Test
    public void test_saveUserPlaylist_hasSizeOne() {
        UserPlaylist playlist = UserPlaylist.builder()
            .title("pop punk")
            .total(0)
            .build();

        userPlaylistRepository.save(playlist);
        List<UserPlaylist> playlistList = userPlaylistRepository.findAll();

        assertThat(playlistList).hasSize(1);
    }

   @Test
   public void test_userPlaylistTitleShouldBeNotNull_ThrowsException() {
       UserPlaylist playlist = UserPlaylist.builder()
           .total(0)
           .build();

       assertThrows(DataIntegrityViolationException.class, () ->
           userPlaylistRepository.save(playlist));
   }

   @Test
    public void test_userPlaylistTrackIsPersistedWithUserPlaylist_hasSizeOne() {
       UserPlaylistTrack track = UserPlaylistTrack.builder()
           .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
           .header("What's My Age Again?")
           .footer("blink-182")
           .build();

       UserPlaylist playlist = UserPlaylist.builder()
           .title("pop punk")
           .userPlaylistTrack(track)
           .total(0)
           .build();

       track.setUserPlaylist(playlist);
       userPlaylistRepository.save(playlist);

       List<UserPlaylistTrack> trackList = userPlaylistTrackRepository.findAll();
       assertThat(trackList)
           .hasSize(1)
           .allMatch(userPlaylistTrack -> userPlaylistTrack.getId() > 0L);
   }
}