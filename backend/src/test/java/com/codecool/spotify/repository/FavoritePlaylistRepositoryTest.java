package com.codecool.spotify.repository;

import com.codecool.spotify.model.Album;
import com.codecool.spotify.model.Playlist;
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
}