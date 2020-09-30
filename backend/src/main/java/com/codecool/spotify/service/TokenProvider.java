package com.codecool.spotify.service;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenProvider {

   public static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

   public static final String SPOTIFY_CLIENT_ID="bcd882684d1747f0b39aed505175f3f4";

   public static final String SPOTIFY_CLIENT_SECRET="f8455f7a4778421b9b52d086214fd873";

   private long startTime;

   private long endTime;

   private int fetchCounter = 0;

   private String token;

   public String getAccessToken() {
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

   private String getToken() {
      try {
         String encoding = Base64.getEncoder().encodeToString((SPOTIFY_CLIENT_ID+ ":" +SPOTIFY_CLIENT_SECRET).getBytes(StandardCharsets.UTF_8));
         JsonNode jsonResponse = Unirest.post(TOKEN_URL)
                 .header("Content-Type", "application/x-www-form-urlencoded")
                 .header("Authorization","Basic " + encoding)
                 .field("grant_type", "client_credentials")
                 .asJson()
                 .getBody();
         return (String) jsonResponse.getObject().get("access_token");
      } catch(Exception e) {
         e.printStackTrace();
      }
      return "";
   }


}
