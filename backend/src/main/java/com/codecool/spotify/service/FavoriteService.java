package com.codecool.spotify.service;

import com.codecool.spotify.model.*;
import com.codecool.spotify.repository.*;
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

   public void addNewFavoriteTrack(Track newTrack) {
      newTrack.setFavorite(true);
      favoriteTrackRepository.save(newTrack);
   }

   public void addNewFavoriteAlbum(Album newAlbum) {
      newAlbum.setFavorite(true);
      favoriteAlbumRepository.save(newAlbum);
   }

   public void addNewFavoritePlaylist(Playlist newPlaylist) {
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
