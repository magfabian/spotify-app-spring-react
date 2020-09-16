package com.codecool.spotify.controller;

import com.codecool.spotify.model.Card;
import com.codecool.spotify.service.DataProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

   @Autowired
   private DataProvider dataCreator;

   @CrossOrigin
   @GetMapping("/new-releases")
   public List<Card> handleNewReleases() throws IOException, JSONException {
      return dataCreator.provideNewReleases();
   }

   @CrossOrigin
   @GetMapping("/search/tracks/{searchString}")
   public List<Card> handleTrackSearch(@PathVariable String searchString) throws IOException, JSONException {
      return dataCreator.provideTracks(searchString);
   }

   @CrossOrigin
   @GetMapping("/search/playlists/{searchString}")
   public List<Card> handlePlaylistSearch(@PathVariable String searchString) throws IOException, JSONException {
      return dataCreator.providePlaylists(searchString);
   }

   @GetMapping("/search/albums/{searchString}")
   public List<Card> handleAlbumSearch(@PathVariable String searchString) throws IOException, JSONException {
      return dataCreator.provideSearchedAlbums(searchString);
   }
}
