package com.codecool.spotify.repository;

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
class UserPlaylistTrackRepositoryTest {

    @Autowired
    private UserPlaylistTrackRepository userPlaylistTrackRepository;

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;

    @Test
    public void test_saveUserPlaylistTrack_hasSizeOne() {
        UserPlaylistTrack track = UserPlaylistTrack.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .header("What's My Age Again?")
            .footer("blink-182")
            .build();

        userPlaylistTrackRepository.save(track);
        List<UserPlaylistTrack> trackList = userPlaylistTrackRepository.findAll();

        assertThat(trackList).hasSize(1);
    }

    @Test
    public void test_saveSameUserPlaylistTrackTwice_ThrowsException() {
        UserPlaylistTrack track = UserPlaylistTrack.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .header("What's My Age Again?")
            .footer("blink-182")
            .build();

        UserPlaylistTrack track2 = UserPlaylistTrack.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .build();

        userPlaylistTrackRepository.save(track);

        assertThrows(DataIntegrityViolationException.class, () ->
            userPlaylistTrackRepository.saveAndFlush(track2));
    }

    @Test
    public void test_userPlaylistTrackSpotifyIdShouldBeNotNull_ThrowsException() {
        UserPlaylistTrack track = UserPlaylistTrack.builder()
            .header("What's My Age Again?")
            .footer("blink-182")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            userPlaylistTrackRepository.save(track));
    }
}