package com.codecool.spotify.controller;

import com.codecool.spotify.model.favorite.*;
import com.codecool.spotify.service.FavoriteService;
import com.codecool.spotify.utility.PrincipalFinder;
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
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.addNewFavoriteTrack(principalEmail, track);
    }

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteAlbum(@RequestBody Album album) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.addNewFavoriteAlbum(principalEmail, album);
    }

    @PostMapping("/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoritePlaylist(@RequestBody Playlist playlist) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.addNewFavoritePlaylist(principalEmail, playlist);
    }

    @PostMapping("/artist")
    @ResponseStatus(HttpStatus.CREATED)
    public void handleNewFavoriteArtist(@RequestBody Artist artist) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.addNewFavoriteArtist(principalEmail, artist);
    }

    @DeleteMapping("/delete/artist/{spotifyId}")
    public void handleDeleteFavouriteArtist(@PathVariable(name = "spotifyId") String spotifyId) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.deleteFavoriteArtist(principalEmail, spotifyId);
    }

    @DeleteMapping("/delete/album/{spotifyId}")
    public void handleDeleteFavouriteAlbum(@PathVariable(name = "spotifyId") String spotifyId) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.deleteFavoriteAlbum(principalEmail, spotifyId);
    }

    @DeleteMapping("/delete/track/{spotifyId}")
    public void handleDeleteFavouriteTrack(@PathVariable(name = "spotifyId") String spotifyId) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.deleteFavoriteTrack(principalEmail, spotifyId);
    }

    @DeleteMapping("/delete/playlist/{spotifyId}")
    public void handleDeleteFavouritePlaylist(@PathVariable(name = "spotifyId") String spotifyId) {
        String principalEmail = PrincipalFinder.getCurrentlyLoggedInUserEmail();
        favoriteService.deleteFavoritePlaylist(principalEmail, spotifyId);
    }
}
