package com.codecool.spotify.controller;


import com.codecool.spotify.model.*;
import com.codecool.spotify.service.FavoriteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteTrack(@RequestBody Track track) {
        favoriteProvider.addNewFavoriteTrack(track);
    }

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteAlbum(@RequestBody Album album) {
        favoriteProvider.addNewFavoriteAlbum(album);
    }

    @PostMapping("/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoritePlaylist(@RequestBody Playlist playlist) {
        favoriteProvider.addNewFavoritePlaylist(playlist);
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteArtist(@RequestBody Artist artist) {
        favoriteProvider.addNewFavoriteArtist(artist);
    }

    @DeleteMapping("/delete/artist")
    public void handleDeleteFavouriteArtist(@RequestBody Artist artist) {
        favoriteProvider.deleteFavoriteArtist(artist);
    }

    @DeleteMapping("/delete/album")
    public void handleDeleteFavouriteArtist(@RequestBody Album album) {
        favoriteProvider.deleteFavoriteAlbum(album);
    }

    @DeleteMapping("/delete/track")
    public void handleDeleteFavouriteArtist(@RequestBody Track track) {
        favoriteProvider.deleteFavoriteTrack(track);
    }

    @DeleteMapping("/delete/playlist")
    public void handleDeleteFavouriteArtist(@RequestBody Playlist playlist) {
        favoriteProvider.deleteFavoritePlaylist(playlist);
    }
}
