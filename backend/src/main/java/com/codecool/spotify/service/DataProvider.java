package com.codecool.spotify.service;

import com.codecool.spotify.model.Card;
import com.codecool.spotify.utility.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataProvider {

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

   public List<Card> provideNewReleases() throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = NEW_RELEASES_URL;
      JSONObject json = remoteURLReader.readFromUrl(url,token);
      return jsonParser.getParsedAlbums(json);
   }

   public List<Card> provideAlbums(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + ALBUM_TYPE ;
      JSONObject json = remoteURLReader.readFromUrl(url,token);
      return jsonParser.getParsedAlbums(json);
   }

   public List<Card> provideTracks(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + TRACK_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedTracks(json);
   }

   public List<Card> providePlaylists(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + PLAYLIST_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedPlaylists(json);
   }

   public List<Card> provideArtists(String searchString) throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = SEARCH_URL + searchString + ARTIST_TYPE;
      JSONObject json = remoteURLReader.readFromUrl(url, token);
      return jsonParser.getParsedSearchedArtist(json);
   }
}
