package com.codecool.spotify.controller;


import com.codecool.spotify.model.*;
import com.codecool.spotify.service.FavoriteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteProvider favoriteProvider;

    @CrossOrigin
    @GetMapping("/get-all")
    public Favorite handleAllFavorites() {
        return favoriteProvider.provideAllFavorites();
    }

    @PostMapping("/track")
    public void handleNewFavoriteTrack(@RequestBody Track track) {
        favoriteProvider.addNewFavoriteTrack(track);
    }

    @PostMapping("/album")
    public void handleNewFavoriteAlbum(@RequestBody Album album) {
        favoriteProvider.addNewFavoriteAlbum(album);
    }

    @PostMapping("/playlist")
    public void handleNewFavoritePlaylist(@RequestBody Playlist playlist) {
        favoriteProvider.addNewFavoritePlaylist(playlist);
    }

    @PostMapping("/artist")
    public void handleNewFavoriteArtist(@RequestBody Artist artist) {
        favoriteProvider.addNewFavoriteArtist(artist);

    }
}
