package com.codecool.spotify.controller;

import com.codecool.spotify.model.UserPlaylist;
import com.codecool.spotify.model.UserPlaylistTrack;
import com.codecool.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/playlist")
public class UserPlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/get-all")
    public List<UserPlaylist> handleAllPlaylist() {
        return playlistService.getAllPlaylists();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewPlaylist(@RequestBody String playlist) {
        playlistService.addNewPlaylist(playlist);
    }

    @PostMapping("/track/{playlist}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewTrack(@PathVariable(name = "playlist") String playlist, @RequestBody UserPlaylistTrack userPlaylistTrack) {
        playlistService.addNewTrackToPlaylist(playlist, userPlaylistTrack);
    }

    @GetMapping("/{playlist}")
    public UserPlaylist handlePlaylist(@PathVariable(name = "playlist") String playlist) {
        return playlistService.getSpecificPlaylist(playlist);
    }

    @DeleteMapping("/delete/{playlist}")
    public void handleDeletePlaylist(@RequestBody UserPlaylist userPlaylist) {
        playlistService.deletePlaylist(userPlaylist);
    }

    @PostMapping("/delete/track/{playlistId}")
    public void handleDeleteTrackFromPlaylist(@PathVariable(name = "playlistId") String playlistId, @RequestBody UserPlaylistTrack track) {
        Long id = Long.parseLong(playlistId);
        playlistService.deleteTrackFromPlaylist(id, track);
    }

    @PostMapping("/edit/")
    public UserPlaylist handleEditPlaylistTitle(@RequestBody UserPlaylist userPlaylist) {
        return playlistService.editPlaylistTitle(userPlaylist);
    }
}
