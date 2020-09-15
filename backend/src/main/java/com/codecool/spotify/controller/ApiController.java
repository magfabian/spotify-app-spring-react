package com.codecool.spotify.controller;

import com.codecool.spotify.model.Card;
import com.codecool.spotify.service.DataProvider;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
