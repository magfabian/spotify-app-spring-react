package com.codecool.spotify.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class TokenProvider {

   public static final String TOKEN_URL = "http://localhost:8888/token";
   @Autowired
   private RemoteURLReader remoteURLReader;

   private long startTime;

   private long endTime;

   private int fetchCounter = 0;

   private String token;

   public String getAccessToken() throws IOException, JSONException {
      if (fetchCounter == 0) {
         startTime = System.currentTimeMillis();
         endTime = System.currentTimeMillis();
         token = getToken();
         fetchCounter++;
      } else {
         endTime = System.currentTimeMillis();
         if (endTime - startTime > 3600000) {
            token = getToken();
            startTime = System.currentTimeMillis();
            fetchCounter++;
         }
      }
      return token;
   }

   private String getToken() throws IOException, JSONException {
      JSONObject json = remoteURLReader.readFromUrl(TOKEN_URL);
      String token = json.get("token").toString();
      System.out.println(token);
      return token;
   }


}
