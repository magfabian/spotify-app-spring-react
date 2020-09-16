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

   public List<Card> getParsedNewReleases(JSONObject json) throws JSONException {
      List<Card> data = new ArrayList<>();
      JSONObject albums = json.getJSONObject("albums");
      JSONArray items = albums.getJSONArray("items");
      for(int i=0; i<items.length(); i++) {
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
      System.out.println(json);
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
}
