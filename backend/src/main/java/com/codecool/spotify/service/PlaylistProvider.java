package com.codecool.spotify.service;

import com.codecool.spotify.model.Card;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistProvider {

    private Map<String, List<Card>> playlists = new HashMap<>();

    public Map<String, List<Card>> getAllPlaylists() {
        return this.playlists;
    }

    public void addNewPlaylist(String title) {
        playlists.put(title, new ArrayList<>());
    }

    public void addNewTrack(String title, Card card) {
        playlists.get(title).add(card);
    }
}
