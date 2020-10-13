package com.codecool.spotify.service;

import com.codecool.spotify.model.favorite.Album;
import com.codecool.spotify.model.favorite.Artist;
import com.codecool.spotify.model.favorite.Playlist;
import com.codecool.spotify.model.favorite.Track;
import com.codecool.spotify.utility.JsonParser;
import com.codecool.spotify.utility.RemoteURLReader;
import com.codecool.spotify.utility.TokenProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataService {

   public static final String NEW_RELEASES_URL = "https://api.spotify.com/v1/browse/new-releases";
   private static final String SEARCH_URL = "https://api.spotify.com/v1/search?q=";
   private static final String TRACK_TYPE = "&type=track";
   private static final String PLAYLIST_TYPE = "&type=playlist";
   public static final String ALBUM_TYPE = "&type=album";
   public static final String ARTIST_TYPE = "&type=artist";

   @Autowired
   private RemoteURLReader remoteURLReader;

   @Autowired
   private TokenProvider tokenProvider;

   @Autowired
   private JsonParser jsonParser;

   public List<Album> provideNewReleases() throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      JSONObject json = remoteURLReader.readFromUrl(NEW_RELEASES_URL,token);
      return jsonParser.getParsedAlbums(json);
   }

   public List<Album> provideAlbums(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + ALBUM_TYPE ;
      JSONObject json = remoteURLReader.readFromUrl(url,token);
      return jsonParser.getParsedAlbums(json);
   }

   public List<Track> provideTracks(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + TRACK_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedTracks(json);
   }

   public List<Playlist> providePlaylists(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + PLAYLIST_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedPlaylists(json);
   }

   public List<Artist> provideArtists(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + ARTIST_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedArtist(json);
   }
}
