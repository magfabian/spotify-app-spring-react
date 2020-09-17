package com.codecool.spotify.controller;

import com.codecool.spotify.model.Card;
import com.codecool.spotify.model.Playlist;
import com.codecool.spotify.service.PlaylistProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    PlaylistProvider playlistProvider;

    @CrossOrigin
    @GetMapping("/get-all")
    public List<Playlist> handleAllPlaylist() {
        return playlistProvider.getAllPlaylists();
    }

    @PostMapping("/new/{playlist}")
    public void handleNewPlaylist(@PathVariable String playlist) {
        playlistProvider.addNewPlaylist(playlist);
    }

    @PostMapping("/track/{playlist}/")
    public void handleNewTrack(@PathVariable String playlist, @RequestBody Card card) {
        playlistProvider.getSpecificPlaylist(playlist).addNewTrackToPlaylist(card);
    }

    @GetMapping("{playlist}")
    public Playlist handlePlaylist(@PathVariable String playlist) {
        return playlistProvider.getSpecificPlaylist(playlist);
    }
}
