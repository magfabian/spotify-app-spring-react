package com.codecool.spotify.controller;


import com.codecool.spotify.model.*;
import com.codecool.spotify.service.FavoriteProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

   @Autowired
   private FavoriteProvider favoriteProvider;

   @CrossOrigin
   @GetMapping("/get-all")
   public Map<String, Set<Card>> handleAllFavorites() {
      return favoriteProvider.provideAllFavorites();
   }

   @PostMapping("/track")
   public boolean handleNewFavoriteTrack(@RequestBody Track track) {
      try {
         favoriteProvider.addNewFavoriteTrack(track);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/album")
   public boolean handleNewFavoriteAlbum(@RequestBody Album album) {
      try {
         favoriteProvider.addNewFavoriteAlbum(album);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/playlist")
   public boolean handleNewFavoritePlaylist(@RequestBody Playlist playlist) {
      try {
         favoriteProvider.addNewFavoritePlaylist(playlist);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/artist")
   public boolean handleNewFavoriteArtist(@RequestBody Artist artist) {
      try {
         favoriteProvider.addNewFavoriteArtist(artist);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }
}
