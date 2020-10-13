package com.codecool.spotify.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.codecool.spotify.model.favorite.Album;
import com.codecool.spotify.model.favorite.Artist;
import com.codecool.spotify.model.favorite.Playlist;
import com.codecool.spotify.model.favorite.Track;
import com.codecool.spotify.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @Test
    public void test_NewReleasesEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        List<Album> testAlbums = List.of(Album.builder().spotifyId("test").build());

        when(dataService.provideNewReleases()).thenReturn(testAlbums);

        this.mockMvc.perform(get("/api/new-releases")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spotifyId").value("test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(dataService, times(1)).provideNewReleases();
    }

    @Test
    public void test_NonExistsEndpoint_ShouldNotWork() throws Exception {
        this.mockMvc.perform(get("/api/dummy"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_TrackSearchEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        String searchString = "test";

        List<Track> testTracks = List.of(Track.builder().spotifyId("test").build());

        when(dataService.provideTracks(searchString)).thenReturn(testTracks);

        this.mockMvc.perform(get("/api/search/tracks/" + searchString)
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spotifyId").value("test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(dataService, times(1)).provideTracks(searchString);
    }

    @Test
    public void test_PlaylistSearchEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        String searchString = "test";

        List<Playlist> testPlaylists = List.of(Playlist.builder().spotifyId("test").build());

        when(dataService.providePlaylists(searchString)).thenReturn(testPlaylists);

        this.mockMvc.perform(get("/api/search/playlists/" + searchString)
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spotifyId").value("test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(dataService, times(1)).providePlaylists(searchString);
    }

    @Test
    public void test_AlbumSearchEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        String searchString = "test";

        List<Album> testAlbums = List.of(Album.builder().spotifyId("test").build());

        when(dataService.provideAlbums(searchString)).thenReturn(testAlbums);

        this.mockMvc.perform(get("/api/search/albums/" + searchString)
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spotifyId").value("test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(dataService, times(1)).provideAlbums(searchString);
    }

    @Test
    public void test_ArtistSearchEndpoint_ShouldRunAndGetArrayFromService() throws Exception {
        String searchString = "test";

        List<Artist> testArtists = List.of(Artist.builder().spotifyId("test").build());

        when(dataService.provideArtists(searchString)).thenReturn(testArtists);

        this.mockMvc.perform(get("/api/search/artists/" + searchString)
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].spotifyId").value("test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(dataService, times(1)).provideArtists(searchString);
    }


}