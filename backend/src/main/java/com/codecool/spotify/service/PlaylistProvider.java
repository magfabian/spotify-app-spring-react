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
    private Map<String, Integer> playlistsLength = new HashMap<>();

    public Map<String, List<Card>> getAllPlaylists() {
        return this.playlists;
    }

    public void addNewPlaylist(String title) {
        playlistsLength.put(title, 0);
        playlists.put(title, new ArrayList<>());
    }

    public void addNewTrack(String title, Card card) {
        playlistsLength.remove(title, playlistsLength.get(title));
        playlistsLength.put(title, playlists.get(title).size());
        playlists.get(title).add(card);
    }

    public Map<String, Integer> getPlaylistsLength() {
        return playlistsLength;
    }
}
