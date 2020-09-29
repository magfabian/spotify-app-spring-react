package com.codecool.spotify.service;

import com.codecool.spotify.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteProvider {

   @Autowired
   private FavoriteProvider favoriteProvider;

//   private Set<Card> tracks = new HashSet<>();
//   private Set<Card> albums = new HashSet<>();
//   private Set<Card> playlists = new HashSet<>();
//   private Set<Card> artists = new HashSet<>();

   public Map<String,Set<Card>> provideAllFavorites() {
      Map<String,Set<Card>> favorites = new HashMap();
      favorites.put("tracks",favoriteProvider.);
      favorites.put("albums",albums);
      favorites.put("playlists",playlists);
      favorites.put("artists",artists);
      return  favorites;
   }

   public void addNewFavoriteTrack(Card card) {
      boolean contains = false;
      for (Card track:tracks) {
         if (track.getImageUrl().equals(card.getImageUrl())) {
            contains = true;
            break;
         }
      }
      if (!contains) {
         tracks.add(card);
      }
   }

   public void addNewFavoriteAlbum(Card card) {
      boolean contains = false;
      for (Card album:albums) {
         if (album.getImageUrl().equals(card.getImageUrl())) {
            contains = true;
            break;
         }
      }
      if (!contains) {
         albums.add(card);
      }
   }

   public void addNewFavoritePlaylist(Card card) {
      boolean contains = false;
      for (Card playlist:playlists) {
         if (playlist.getImageUrl().equals(card.getImageUrl())) {
            contains = true;
            break;
         }
      }
      if (!contains) {
         playlists.add(card);
      }
   }

   public void addNewFavoriteArtist(Card card) {
      boolean contains = false;
      for (Card artist:artists) {
         if (artist.getImageUrl().equals(card.getImageUrl())) {
            contains = true;
            break;
         }
      }
      if (!contains) {
         artists.add(card);
      }
   }
}
