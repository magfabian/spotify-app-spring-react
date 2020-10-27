package com.codecool.spotify.service;

import com.codecool.spotify.model.favorite.*;
import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.repository.favorite.FavoriteAlbumRepository;
import com.codecool.spotify.repository.favorite.FavoriteArtistRepository;
import com.codecool.spotify.repository.favorite.FavoritePlaylistRepository;
import com.codecool.spotify.repository.favorite.FavoriteTrackRepository;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FavoriteService {

   @Autowired
   private SpotiUserRepository spotiUserRepository;

   @Autowired
   private FavoriteAlbumRepository favoriteAlbumRepository;

   @Autowired
   private FavoriteArtistRepository favoriteArtistRepository;

   @Autowired
   private FavoritePlaylistRepository favoritePlaylistRepository;

   @Autowired
   private FavoriteTrackRepository favoriteTrackRepository;

   public Favorite provideAllFavorites(String email) {
      SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      return Favorite.builder()
              .albums(favoriteAlbumRepository.findAllBySpotiUser(spotiUser))
              .artists(favoriteArtistRepository.findAllBySpotiUser(spotiUser))
              .playlists(favoritePlaylistRepository.findAllBySpotiUser(spotiUser))
              .tracks(favoriteTrackRepository.findAllBySpotiUser(spotiUser))
              .build();
   }

   public void addNewFavoriteTrack(String email, Track newTrack) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      newTrack.setSpotiUser(user);
      newTrack.setFavorite(true);

      favoriteTrackRepository.save(newTrack);
   }

   public void addNewFavoriteAlbum(String email, Album newAlbum) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      newAlbum.setSpotiUser(user);
      newAlbum.setFavorite(true);

      favoriteAlbumRepository.save(newAlbum);
   }

   public void addNewFavoritePlaylist(String email, Playlist newPlaylist) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      newPlaylist.setSpotiUser(user);
      newPlaylist.setFavorite(true);

      favoritePlaylistRepository.save(newPlaylist);
   }

   public void addNewFavoriteArtist(String email, Artist newArtist) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      newArtist.setSpotiUser(user);
      newArtist.setFavorite(true);

      favoriteArtistRepository.save(newArtist);
   }

   @Transactional
   public void deleteFavoriteArtist(String email, String spotifyId) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      favoriteArtistRepository.deleteArtistBySpotifyId(user, spotifyId);
   }

   @Transactional
   public void deleteFavoriteAlbum(String email, String spotifyId) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      favoriteAlbumRepository.deleteAlbumBySpotifyId(user, spotifyId);
   }

   @Transactional
   public void deleteFavoriteTrack(String email, String spotifyId) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      favoriteTrackRepository.deleteTrackBySpotifyId(user, spotifyId);
   }

   @Transactional
   public void deleteFavoritePlaylist(String email, String spotifyId) {
      SpotiUser user = spotiUserRepository.findSpotiUserByEmailAddress(email)
          .orElseThrow(() -> new UsernameNotFoundException("No user was found"));

      favoritePlaylistRepository.deletePlaylistBySpotifyId(user, spotifyId);
   }
}
