package com.codecool.spotify.controller;

import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
import com.codecool.spotify.service.PlaylistService;
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

@WebMvcTest(UserPlaylistController.class)
class UserPlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @Test
    public void test_GetAllEndpoint_ShouldRunAndGetArrayFromService() throws Exception {

        UserPlaylist playlist = UserPlaylist.builder()
                .total(3)
                .build();

        List<UserPlaylist> playlists = List.of(playlist);

        when(playlistService.getAllPlaylists()).thenReturn(playlists);

        this.mockMvc.perform(get("/playlist/get-all")
                .accept("application/json"))
                .andExpect(jsonPath("$[0].total").value("3"))
                .andExpect(status().isOk());

        verify(playlistService, times(1)).getAllPlaylists();
    }

    @Test
    public void test_NonExistsEndpoint_ShouldNotWork() throws Exception {
        this.mockMvc.perform(get("/playlist/test/dummy"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_NewUserPlaylistEndpoint_ShouldRunAndSave() throws Exception {
        this.mockMvc.perform( post("/playlist/new")
                .content(asJsonString("test"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(playlistService, times(1)).addNewPlaylist(any(String.class));
    }

    @Test
    public void test_NewTrackToPlaylistEndpoint_ShouldRunAndSave() throws Exception {
        UserPlaylistTrack userPlaylistTrack = UserPlaylistTrack.builder()
                .spotifyId("test")
                .build();

        this.mockMvc.perform( post("/playlist/track/test")
                .content(asJsonString(userPlaylistTrack))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(playlistService, times(1))
                .addNewTrackToPlaylist(any(String.class),any(UserPlaylistTrack.class));
    }

    @Test
    public void test_SpecificPlaylistEndpoint_ShouldRunAndGetTracksFromService() throws Exception {

        UserPlaylist playlist = UserPlaylist.builder()
                .total(3)
                .build();

        when(playlistService.getSpecificPlaylist("test")).thenReturn(playlist);

        this.mockMvc.perform(get("/playlist/test")
                .accept("application/json"))
                .andExpect(jsonPath("$.total").value("3"))
                .andExpect(status().isOk());

        verify(playlistService, times(1)).getSpecificPlaylist(any(String.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}