package com.codecool.spotify.repository;


import com.codecool.spotify.model.Album;
import com.codecool.spotify.model.Artist;
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
class FavoriteArtistRepositoryTest {

    @Autowired
    private FavoriteArtistRepository favoriteArtistRepository;

    @Test
    public void saveFavoriteArtist() {
        Artist artist = Artist.builder()
            .spotifyId("6GH0NzpthMGxu1mcfAkOde")
            .imageUrl("https://i.scdn.co/image/46eb8bc663f5a161833eea7eee32c60353f23893")
            .onClickUrl("https://open.spotify.com/artist/6GH0NzpthMGxu1mcfAkOde")
            .header("Hellogoodbye")
            .footer("Followers: 173246")
            .footerUrl(null)
            .favorite(true)
            .build();

        favoriteArtistRepository.save(artist);
        List<Artist> artistList = favoriteArtistRepository.findAll();

        assertThat(artistList).hasSize(1);
    }
}