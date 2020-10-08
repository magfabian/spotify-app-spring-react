package com.codecool.spotify.controller;


import com.codecool.spotify.model.*;
import com.codecool.spotify.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
@CrossOrigin(origins = "http://localhost:3000")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/get-all")
    public Favorite handleAllFavorites() {
        return favoriteService.provideAllFavorites();
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteTrack(@RequestBody Track track) {
        favoriteService.addNewFavoriteTrack(track);
    }

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteAlbum(@RequestBody Album album) {
        favoriteService.addNewFavoriteAlbum(album);
    }

    @PostMapping("/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoritePlaylist(@RequestBody Playlist playlist) {
        favoriteService.addNewFavoritePlaylist(playlist);
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteArtist(@RequestBody Artist artist) {
        favoriteService.addNewFavoriteArtist(artist);
    }

    @PostMapping("/delete/artist")
    public void handleDeleteFavouriteArtist(@RequestBody Artist artist) {
        favoriteService.deleteFavoriteArtist(artist);
    }

    @PostMapping("/delete/album")
    public void handleDeleteFavouriteArtist(@RequestBody Album album) {
        favoriteService.deleteFavoriteAlbum(album);
    }

    @PostMapping("/delete/track")
    public void handleDeleteFavouriteArtist(@RequestBody Track track) {
        favoriteService.deleteFavoriteTrack(track);
    }

    @PostMapping("/delete/playlist")
    public void handleDeleteFavouriteArtist(@RequestBody Playlist playlist) {
        favoriteService.deleteFavoritePlaylist(playlist);
    }
}
