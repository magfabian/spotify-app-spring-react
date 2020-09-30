package com.codecool.spotify.repository;

import com.codecool.spotify.model.Album;
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
class FavoriteAlbumRepositoryTest {

    @Autowired
    private FavoriteAlbumRepository favoriteAlbumRepository;

    @Test
    public void saveFavoriteAlbum() {
        Album album = Album.builder()
            .spotifyId("4Bp7LKA5Afo1PRoXuQe8qZ")
            .imageUrl("https://i.scdn.co/image/ab67616d0000b27370f2ab5608885749f7210b5f")
            .onClickUrl("https://open.spotify.com/album/4Bp7LKA5Afo1PRoXuQe8qZ")
            .header("FRANCHISE (feat. Young Thug & M.I.A.)")
            .footer("Travis Scott")
            .footerUrl("https://open.spotify.com/artist/0Y5tJX1MQlPlqiwlOH1tJY")
            .favorite(true)
            .build();

        favoriteAlbumRepository.save(album);
        List<Album> albumList = favoriteAlbumRepository.findAll();

        assertThat(albumList).hasSize(1);
    }

    @Test
    public void saveSameAlbumTwice() {
        Album album = Album.builder()
            .spotifyId("4Bp7LKA5Afo1PRoXuQe8qZ")
            .imageUrl("https://i.scdn.co/image/ab67616d0000b27370f2ab5608885749f7210b5f")
            .onClickUrl("https://open.spotify.com/album/4Bp7LKA5Afo1PRoXuQe8qZ")
            .header("FRANCHISE (feat. Young Thug & M.I.A.)")
            .footer("Travis Scott")
            .footerUrl("https://open.spotify.com/artist/0Y5tJX1MQlPlqiwlOH1tJY")
            .favorite(true)
            .build();

        favoriteAlbumRepository.save(album);

        Album album2 = Album.builder()
            .spotifyId("4Bp7LKA5Afo1PRoXuQe8qZ")
            .favorite(true)
            .build();

        assertThrows(DataIntegrityViolationException.class,  () ->
            favoriteAlbumRepository.saveAndFlush(album2)
        );
    }
}