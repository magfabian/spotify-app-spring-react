package com.codecool.spotify.controller;

import com.codecool.spotify.model.Card;
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
    public Map<String, List<Card>> handleAllPlaylist() {
        return playlistProvider.getAllPlaylists();
    }

    @CrossOrigin
    @GetMapping("/total")
    public Map<String, Integer> handlePlaylistLength() {
        return playlistProvider.getPlaylistsLength();
    }

    @PostMapping("/new/{playlist}")
    public void handleNewPlaylist(@PathVariable String playlist) {
        playlistProvider.addNewPlaylist(playlist);
    }

    @PostMapping("/track/{playlist}/")
    public void handleNewTrack(@PathVariable String playlist, @RequestBody Card card) {
        playlistProvider.addNewTrack(playlist, card);
    }
}
