package com.codecool.spotify.repository;

import com.codecool.spotify.model.Track;
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
class FavoriteTrackRepositoryTest {

    @Autowired
    private FavoriteTrackRepository favoriteTrackRepository;

    @Test
    public void test_saveFavoriteTrack_hasSizeOne() {
        Track track = Track.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .imageUrl("https://i.scdn.co/image/ab67616d0000b2736da502e35a7a3e48de2b0f74")
            .onClickUrl("https://open.spotify.com/track/4LJhJ6DQS7NwE7UKtvcM52")
            .header("What's My Age Again?")
            .footer("blink-182")
            .footerUrl("https://open.spotify.com/artist/6FBDaR13swtiWwGhX1WQsP")
            .favorite(true)
            .build();

        favoriteTrackRepository.save(track);
        List<Track> trackList = favoriteTrackRepository.findAll();

        assertThat(trackList).hasSize(1);
    }

    @Test
    public void test_saveSameTrackTwice_ThrowsException() {
        Track track = Track.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .imageUrl("https://i.scdn.co/image/ab67616d0000b2736da502e35a7a3e48de2b0f74")
            .onClickUrl("https://open.spotify.com/track/4LJhJ6DQS7NwE7UKtvcM52")
            .header("What's My Age Again?")
            .footer("blink-182")
            .footerUrl("https://open.spotify.com/artist/6FBDaR13swtiWwGhX1WQsP")
            .favorite(true)
            .build();

        favoriteTrackRepository.save(track);

        Track track2 = Track.builder()
            .spotifyId("4LJhJ6DQS7NwE7UKtvcM52")
            .header("What's My Age Again?")
            .footer("blink-182")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            favoriteTrackRepository.saveAndFlush(track2)
        );
    }

    @Test
    public void test_trackSpotifyIdShouldBeNotNull_ThrowsException() {
        Track track = Track.builder()
            .header("What's My Age Again?")
            .footer("blink-182")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            favoriteTrackRepository.save(track));
    }
}