package com.codecool.spotify.controller;

import com.codecool.spotify.model.Track;
import com.codecool.spotify.model.UserPlaylist;
import com.codecool.spotify.model.UserPlaylistTrack;
import com.codecool.spotify.service.PlaylistProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewPlaylist(@RequestBody String playlist) {
        playlistProvider.addNewPlaylist(playlist);
    }

    @CrossOrigin
    @PostMapping("/track/{playlist}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewTrack(@PathVariable(name = "playlist") String playlist, @RequestBody UserPlaylistTrack userPlaylistTrack) {
        playlistProvider.addNewTrackToPlaylist(playlist, userPlaylistTrack);
    }

    @CrossOrigin
    @GetMapping("/{playlist}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPlaylist handlePlaylist(@PathVariable(name = "playlist") String playlist) {
        return playlistProvider.getSpecificPlaylist(playlist);
    }

    @DeleteMapping("/delete/track/{playlist}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleDeleteTrackFromPlaylist(@PathVariable(name = "playlist") String playlist, @RequestBody UserPlaylistTrack track) {
        playlistProvider.deleteTrackFromPlaylist(playlist, track);
    }
}
