package com.codecool.spotify.service;

import com.codecool.spotify.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistProvider {

    private List<Playlist> playlists = new ArrayList<>();

    public List<Playlist> getAllPlaylists() {
        return this.playlists;
    }

    public void addNewPlaylist(String title) {
       playlists.add(new Playlist(title));
    }

    public Playlist getSpecificPlaylist(String title) {
        return playlists.stream().filter(playlist -> playlist.getTitle().equals(title)).findFirst().get();
    }
}
