package com.codecool.spotify.controller;

import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
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
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        return playlistService.getAllPlaylists(principalEmail);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewPlaylist(@RequestBody String playlist) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        playlistService.addNewPlaylist(principalEmail, playlist);
    }

    @PostMapping("/track/{playlist}")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewTrack(@PathVariable(name = "playlist") String playlist, @RequestBody UserPlaylistTrack userPlaylistTrack) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        playlistService.addNewTrackToPlaylist(principalEmail, playlist, userPlaylistTrack);
    }

    @GetMapping("/{playlist}")
    public UserPlaylist handlePlaylist(@PathVariable(name = "playlist") String playlist) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        return playlistService.getSpecificPlaylist(principalEmail, playlist);
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
