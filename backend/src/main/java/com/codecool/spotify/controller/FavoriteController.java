package com.codecool.spotify.controller;


import com.codecool.spotify.model.Card;
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
   public boolean handleNewFavoriteTrack(@RequestBody Card card) {
      try {
         favoriteProvider.addNewFavoriteTrack(card);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/album")
   public boolean handleNewFavoriteAlbum(@RequestBody Card card) {
      try {
         favoriteProvider.addNewFavoriteAlbum(card);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/playlist")
   public boolean handleNewFavoritePlaylist(@RequestBody Card card) {
      try {
         favoriteProvider.addNewFavoritePlaylist(card);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }

   @PostMapping("/artist")
   public boolean handleNewFavoriteArtist(@RequestBody Card card) {
      try {
         favoriteProvider.addNewFavoriteArtist(card);
         return true;
      } catch (Exception ex) {
         ex.printStackTrace();
         return false;
      }
   }
}
