package com.codecool.spotify.model;

import java.util.ArrayList;
import java.util.List;

public class UserPlaylist {
    private String title;
    private int total = 0;
    private List<Card> tracks;

    public UserPlaylist(String title) {
        this.title = title;
        this.tracks = new ArrayList<>();
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
        boolean contains = false;
        for (Card track:tracks) {
            if (track.getImageUrl().equals(card.getImageUrl())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            tracks.add(card);
            total ++;
        }
    }
}
