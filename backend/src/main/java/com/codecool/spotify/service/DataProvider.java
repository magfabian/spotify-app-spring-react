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
   private static final String ACCESS_TOKEN_URL = "?access_token=";

   @Autowired
   private RemoteURLReader remoteURLReader;

   @Autowired
   private TokenProvider tokenProvider;

   @Autowired
   private JsonParser jsonParser;

   public List<Card> provideNewReleases() throws IOException, JSONException {
      String token = tokenProvider.getAccessToken();
      String url = NEW_RELEASES_URL + ACCESS_TOKEN_URL + token;
      JSONObject json = remoteURLReader.readFromUrl(url);
      return jsonParser.getParsedNewReleases(json);
   }


}
