package com.codecool.spotify.utility;

import com.codecool.spotify.model.Album;
import com.codecool.spotify.model.Artist;
import com.codecool.spotify.model.Playlist;
import com.codecool.spotify.model.Track;
import com.codecool.spotify.repository.FavoriteAlbumRepository;
import com.codecool.spotify.repository.FavoriteArtistRepository;
import com.codecool.spotify.repository.FavoritePlaylistRepository;
import com.codecool.spotify.repository.FavoriteTrackRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

   @Autowired
   private FavoriteAlbumRepository favoriteAlbumRepository;

   @Autowired
   private FavoriteArtistRepository favoriteArtistRepository;

   @Autowired
   private FavoritePlaylistRepository favoritePlaylistRepository;

   @Autowired
   private FavoriteTrackRepository favoriteTrackRepository;



   public List<Album> getParsedAlbums(JSONObject json) throws JSONException {
      List<Album> data = new ArrayList<>();
      JSONObject albums = json.getJSONObject("albums");
      JSONArray items = albums.getJSONArray("items");
      for(int i = 0; i < items.length(); i++) {
         JSONObject album = items.getJSONObject(i);
         String imageUrl = album.getJSONArray("images").getJSONObject(0).get("url").toString();
         String header = album.get("name").toString();
         String footer = album.getJSONArray("artists").getJSONObject(0).get("name").toString();
         String onClickUrl = album.getJSONObject("external_urls").get("spotify").toString();
         String footerUrl = album.getJSONArray("artists")
                 .getJSONObject(0).getJSONObject("external_urls").get("spotify").toString();
         String id = album.get("id").toString();
         Album generatedAlbum = Album.builder()
             .spotifyId(id)
             .header(header)
             .imageUrl(imageUrl)
             .onClickUrl(onClickUrl)
             .footer(footer)
             .footerUrl(footerUrl)
             .favorite(isFavoriteAlbum(id))
             .build();
         data.add(generatedAlbum);
      }
      return data;
   }

   public List<Track> getParsedSearchedTracks(JSONObject json) throws JSONException {
      List<Track> data = new ArrayList<>();
      JSONObject tracks = json.getJSONObject("tracks");
      JSONArray items = tracks.getJSONArray("items");

      for (int i = 0; i < items.length(); i++) {
         JSONObject track = items.getJSONObject(i);
         String imageUrl = track.getJSONObject("album").getJSONArray("images").getJSONObject(0).get("url").toString();
         String header = track.get("name").toString();
         String footer = track.getJSONArray("artists").getJSONObject(0).get("name").toString();
         String onClickUrl = track.getJSONObject("external_urls").get("spotify").toString();
         String footerUrl = track.getJSONArray("artists").getJSONObject(0).getJSONObject("external_urls").get("spotify").toString();
         String id = track.get("id").toString();
         Track generatedTrack = Track.builder()
             .spotifyId(id)
             .header(header)
             .imageUrl(imageUrl)
             .onClickUrl(onClickUrl)
             .footer(footer)
             .footerUrl(footerUrl)
             .favorite(isFavoriteTrack(id))
             .build();
         data.add(generatedTrack);
      }
      return data;
   }

   public List<Playlist> getParsedSearchedPlaylists(JSONObject json) throws JSONException {
      List<Playlist> data = new ArrayList<>();
      JSONObject playlists = json.getJSONObject("playlists");
      JSONArray items = playlists.getJSONArray("items");

      for (int i = 0; i < items.length(); i++) {
         JSONObject playlist = items.getJSONObject(i);
         String imageUrl = playlist.getJSONArray("images").getJSONObject(0).get("url").toString();
         String header = playlist.get("name").toString();
         String footer = "Tracks: "  + playlist.getJSONObject("tracks").get("total");
         String onClickUrl = playlist.getJSONObject("external_urls").get("spotify").toString();
         String footerUrl = null;
         String id = playlist.get("id").toString();
         Playlist generatedPlaylist = Playlist.builder()
             .spotifyId(id)
             .header(header)
             .imageUrl(imageUrl)
             .onClickUrl(onClickUrl)
             .footer(footer)
             .footerUrl(footerUrl)
             .favorite(isFavoritePlaylist(id))
             .build();
         data.add(generatedPlaylist);
      }
      return data;
   }

   public List<Artist> getParsedSearchedArtist(JSONObject json) throws JSONException {
      List<Artist> data = new ArrayList<>();
      JSONObject artists = json.getJSONObject("artists");
      JSONArray items = artists.getJSONArray("items");
      for (int i = 0; i < items.length(); i++) {
         JSONObject artist = items.getJSONObject(i);
         String imageUrl;
         try {
            imageUrl = artist.getJSONArray("images").getJSONObject(0).get("url").toString();
         } catch (Exception ex) {
            imageUrl = "https://748073e22e8db794416a-cc51ef6b37841580002827d4d94d19b6.ssl.cf3.rackcdn.com/not-found.png";
         }
         String header = artist.get("name").toString();
         String footer = "Followers: "  + artist.getJSONObject("followers").get("total");
         String onClickUrl = artist.getJSONObject("external_urls").get("spotify").toString();
         String footerUrl = null;
         String id = artist.get("id").toString();
         Artist generatedArtist = Artist.builder()
             .spotifyId(id)
             .header(header)
             .imageUrl(imageUrl)
             .onClickUrl(onClickUrl)
             .footer(footer)
             .footerUrl(footerUrl)
             .favorite(isFavoriteArtist(id))
             .build();
         data.add(generatedArtist);
      }
      return data;
   }

   public boolean isFavoriteAlbum(String id) {
      List<Album> albums = favoriteAlbumRepository.findAll();
      boolean contains = false;
      for (Album album : albums) {
         if (album.getSpotifyId().equals(id)) {
            contains = true;
            break;
         }
      }
      return contains;
   }

   public boolean isFavoriteArtist(String id) {
      List<Artist> artists = favoriteArtistRepository.findAll();
      boolean contains = false;
      for (Artist artist : artists) {
         if (artist.getSpotifyId().equals(id)) {
            contains = true;
            break;
         }
      }
      return contains;
   }

   public boolean isFavoriteTrack(String id) {
      List<Track> tracks = favoriteTrackRepository.findAll();
      boolean contains = false;
      for (Track track : tracks) {
         if (track.getSpotifyId().equals(id)) {
            contains = true;
            break;
         }
      }
      return contains;
   }

   public boolean isFavoritePlaylist(String id) {
      List<Playlist> playlists = favoritePlaylistRepository.findAll();
      boolean contains = false;
      for (Playlist playlist : playlists) {
         if (playlist.getSpotifyId().equals(id)) {
            contains = true;
            break;
         }
      }
      return contains;
   }
}
