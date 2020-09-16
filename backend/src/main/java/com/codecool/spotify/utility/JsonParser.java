package com.codecool.spotify.utility;

import com.codecool.spotify.model.Card;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

   public List<Card> getParsedAlbums(JSONObject json) throws JSONException {
      List<Card> data = new ArrayList<>();
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
         Card card = new Card(id, imageUrl, onClickUrl, header, footer, footerUrl);
         data.add(card);
      }
      return data;
   }

   public List<Card> getParsedSearchedTracks(JSONObject json) throws JSONException {
      List<Card> data = new ArrayList<>();
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
         Card card = new Card(id, imageUrl, onClickUrl, header, footer, footerUrl);
         data.add(card);
      }
      return data;
   }

   public List<Card> getParsedSearchedPlaylists(JSONObject json) throws JSONException {
      List<Card> data = new ArrayList<>();
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
         Card card = new Card(id, imageUrl, onClickUrl, header, footer, footerUrl);
         data.add(card);
      }
      return data;
   }

   public List<Card> getParsedSearchedArtist(JSONObject json) throws JSONException {
      List<Card> data = new ArrayList<>();
      JSONObject artists = json.getJSONObject("artists");
      JSONArray items = artists.getJSONArray("items");
      System.out.println(items.length());
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
         Card card = new Card(id, imageUrl, onClickUrl, header, footer, footerUrl);
         data.add(card);
      }
      return data;
   }
}
