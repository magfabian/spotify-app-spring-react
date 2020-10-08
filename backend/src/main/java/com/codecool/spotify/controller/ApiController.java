package com.codecool.spotify.controller;

import com.codecool.spotify.model.Album;
import com.codecool.spotify.model.Artist;
import com.codecool.spotify.model.Playlist;
import com.codecool.spotify.model.Track;
import com.codecool.spotify.service.DataService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

   @Autowired
   private DataService dataService;

   @CrossOrigin
   @GetMapping("/new-releases")
   public List<Album> handleNewReleases() throws IOException, JSONException {
      return dataService.provideNewReleases();
   }

   @GetMapping("/search/tracks/{searchString}")
   public List<Track> handleTrackSearch(@PathVariable(name = "searchString") String searchString) throws IOException, JSONException {
      return dataService.provideTracks(searchString);
   }

   @GetMapping("/search/playlists/{searchString}")
   public List<Playlist> handlePlaylistSearch(@PathVariable(name = "searchString") String searchString) throws IOException, JSONException {
      return dataService.providePlaylists(searchString);
   }

   @GetMapping("/search/albums/{searchString}")
   public List<Album> handleAlbumSearch(@PathVariable(name = "searchString") String searchString) throws IOException, JSONException {
      return dataService.provideAlbums(searchString);
   }

   @GetMapping("/search/artists/{searchString}")
   public List<Artist> handleArtistSearch(@PathVariable(name = "searchString") String searchString) throws IOException, JSONException {
      return dataService.provideArtists(searchString);
   }
}
