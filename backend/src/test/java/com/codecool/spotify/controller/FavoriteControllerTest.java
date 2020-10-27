package com.codecool.spotify.controller;

import com.codecool.spotify.model.favorite.*;
import com.codecool.spotify.service.FavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FavoriteController.class)
class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteService favoriteService;

    @Test
    public void test_GetAllEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        List<Artist> testArtists = List.of(Artist.builder().spotifyId("test").build());

        Favorite favorite = Favorite.builder()
                .artists(testArtists)
                .build();

//        when(favoriteService.provideAllFavorites()).thenReturn(favorite);

        this.mockMvc.perform(get("/favorite/get-all")
                .accept("application/json"))
                .andExpect(jsonPath("$.artists[0].spotifyId").value("test"))
                .andExpect(status().isOk());

//        verify(favoriteService, times(1)).provideAllFavorites();
    }

    @Test
    public void test_NonExistsEndpoint_ShouldNotWork() throws Exception {
        this.mockMvc.perform(get("/favorite/dummy"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_AlbumFavoriteEndpoint_ShouldRunAndSave() throws Exception {
        Album album = Album.builder()
                .spotifyId("test")
                .build();

        this.mockMvc.perform( post("/favorite/album")
                .content(asJsonString(album))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
// TODO
//        verify(favoriteService, times(1)).addNewFavoriteAlbum(any(Album.class));
    }

    @Test
    public void test_TrackFavoriteEndpoint_ShouldRunAndSave() throws Exception {
        Track track = Track.builder()
                .spotifyId("test")
                .build();

        this.mockMvc.perform( post("/favorite/track")
                .content(asJsonString(track))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

//        verify(favoriteService, times(1)).addNewFavoriteTrack(any(Track.class));
    }

    @Test
    public void test_ArtistFavoriteEndpoint_ShouldRunAndSave() throws Exception {
        Artist artist = Artist.builder()
                .spotifyId("test")
                .build();

        this.mockMvc.perform( post("/favorite/artist")
                .content(asJsonString(artist))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

//        verify(favoriteService, times(1)).addNewFavoriteArtist(any(Artist.class));
    }

    @Test
    public void test_PlaylistFavoriteEndpoint_ShouldRunAndSave() throws Exception {
        Playlist playlist = Playlist.builder()
                .spotifyId("test")
                .build();

        this.mockMvc.perform( post("/favorite/playlist")
                .content(asJsonString(playlist))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

//        verify(favoriteService, times(1)).addNewFavoritePlaylist(any(Playlist.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}