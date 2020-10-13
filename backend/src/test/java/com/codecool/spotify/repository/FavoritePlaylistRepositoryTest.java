package com.codecool.spotify.repository;

import com.codecool.spotify.model.favorite.Playlist;
import com.codecool.spotify.repository.favorite.FavoritePlaylistRepository;
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
class FavoritePlaylistRepositoryTest {

    @Autowired
    private FavoritePlaylistRepository favoritePlaylistRepository;

    @Test
    public void test_saveFavoritePlaylist_hasSizeOne() {
        Playlist playlist = Playlist.builder()
            .spotifyId("37i9dQZF1DWSobRXOCtFPM")
            .header("Carl Cox' track IDs")
            .footer("Tracks: 93")
            .favorite(true)
            .build();

        favoritePlaylistRepository.save(playlist);
        List<Playlist> playlistList = favoritePlaylistRepository.findAll();

        assertThat(playlistList).hasSize(1);
    }

    @Test
    public void test_saveSamePlaylistTwice_ThrowsException() {
        Playlist playlist = Playlist.builder()
            .spotifyId("37i9dQZF1DWSobRXOCtFPM")
            .header("Carl Cox' track IDs")
            .footer("Tracks: 93")
            .favorite(true)
            .build();

        Playlist playlist2 = Playlist.builder()
            .spotifyId("37i9dQZF1DWSobRXOCtFPM")
            .build();

        favoritePlaylistRepository.save(playlist);

        assertThrows(DataIntegrityViolationException.class, () ->
            favoritePlaylistRepository.saveAndFlush(playlist2));
    }

    @Test
    public void test_playlistSpotifyIdShouldBeNotNull() {
        Playlist playlist = Playlist.builder()
            .header("Carl Cox' track IDs")
            .footer("Tracks: 93")
            .favorite(true)
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            favoritePlaylistRepository.save(playlist));
    }
}