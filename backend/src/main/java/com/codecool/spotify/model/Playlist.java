package com.codecool.spotify.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String title;
    private int total;
    private List<Card> tracks;

    public Playlist(String title) {
        this.title = title;
        this.tracks = new ArrayList<>();
        this.total = tracks.size();
    }

    public String getTitle() {
        return title;
    }

    public int getTotal() {
        return total;
    }

    public List<Card> getTracks() {
        return tracks;
    }

    public void addNewTrackToPlaylist(Card card) {
        for (Card track : tracks) {
            if (!track.getImageUrl().equals(card.getImageUrl())) {
                tracks.add(card);
            }
        }
    }
}
