package com.codecool.spotify.service;

import com.codecool.spotify.model.favorite.*;
import com.codecool.spotify.repository.favorite.FavoriteAlbumRepository;
import com.codecool.spotify.repository.favorite.FavoriteArtistRepository;
import com.codecool.spotify.repository.favorite.FavoritePlaylistRepository;
import com.codecool.spotify.repository.favorite.FavoriteTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FavoriteService {

   @Autowired
   private FavoriteAlbumRepository favoriteAlbumRepository;

   @Autowired
   private FavoriteArtistRepository favoriteArtistRepository;

   @Autowired
   private FavoritePlaylistRepository favoritePlaylistRepository;

   @Autowired
   private FavoriteTrackRepository favoriteTrackRepository;

   public Favorite provideAllFavorites() {
      return Favorite.builder()
              .albums(favoriteAlbumRepository.findAll())
              .artists(favoriteArtistRepository.findAll())
              .playlists(favoritePlaylistRepository.findAll())
              .tracks(favoriteTrackRepository.findAll())
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

   public void addNewFavoriteArtist(Artist newArtist) {
      newArtist.setFavorite(true);

      favoriteArtistRepository.save(newArtist);
   }

   @Transactional
   public void deleteFavoriteArtist(Artist artist) {
      favoriteArtistRepository.deleteArtistBySpotifyId(artist.getSpotifyId());
   }

   @Transactional
   public void deleteFavoriteAlbum(Album album) {
      favoriteAlbumRepository.deleteAlbumBySpotifyId(album.getSpotifyId());
   }

   @Transactional
   public void deleteFavoriteTrack(Track track) {
      favoriteTrackRepository.deleteTrackBySpotifyId(track.getSpotifyId());
   }

   @Transactional
   public void deleteFavoritePlaylist(Playlist playlist) {
      favoritePlaylistRepository.deletePlaylistBySpotifyId(playlist.getSpotifyId());
   }
}
