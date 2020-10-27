package com.codecool.spotify.controller;

import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
import com.codecool.spotify.service.PlaylistService;
import com.codecool.spotify.utility.PrincipalFinder;
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

    @DeleteMapping("/delete/{playlistId}")
    public void handleDeletePlaylist(@PathVariable(name = "playlistId") UserPlaylist userPlaylist) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        playlistService.deletePlaylist(principalEmail, userPlaylist);
    }

    @DeleteMapping("/delete/track/{playlistId}/{spotifyId}")
    public void handleDeleteTrackFromPlaylist(@PathVariable(name = "playlistId") String playlistId, @PathVariable(name = "spotifyId") String spotifyId) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();

        playlistService.deleteTrackFromPlaylist(principalEmail, Long.parseLong(playlistId), spotifyId);
    }
}
