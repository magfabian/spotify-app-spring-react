package com.codecool.spotify.controller;

import com.codecool.spotify.model.Track;
import com.codecool.spotify.model.UserPlaylist;
import com.codecool.spotify.service.PlaylistProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class UserPlaylistController {

    @Autowired
    PlaylistProvider playlistProvider;

    @CrossOrigin
    @GetMapping("/get-all")
    public List<UserPlaylist> handleAllPlaylist() {
        return playlistProvider.getAllPlaylists();
    }

    @PostMapping("/new/{playlist}")
    public void handleNewPlaylist(@PathVariable(name = "playlist") String playlist) {
        playlistProvider.addNewPlaylist(playlist);
    }

    @CrossOrigin
    @PostMapping("/track/{playlist}")
    public void handleNewTrack(@PathVariable(name = "playlist") String playlist, @RequestBody Track track) {
        // TODO deleted method
//        playlistProvider.getSpecificPlaylist(playlist).addNewTrackToPlaylist(track);
    }

    @CrossOrigin
    @GetMapping("/{playlist}")
    public UserPlaylist handlePlaylist(@PathVariable(name = "playlist") String playlist) {
        return playlistProvider.getSpecificPlaylist(playlist);
    }
}
