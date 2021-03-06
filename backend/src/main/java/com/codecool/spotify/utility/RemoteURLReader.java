package com.codecool.spotify.utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class RemoteURLReader {

   public JSONObject readFromUrl(String endpoint,String token) throws IOException, JSONException {
      URL url = new URL(endpoint);
      URLConnection conn = url.openConnection();
      String auth = "Bearer " + token;
      conn.setRequestProperty("Authorization",auth);
      String lines;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
         lines = reader.lines().collect(Collectors.joining("\n"));
      }
      return new JSONObject(lines);
   }
}