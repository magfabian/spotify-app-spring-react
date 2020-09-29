package com.codecool.spotify.service;

import com.codecool.spotify.model.UserPlaylist;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistProvider {

    private List<UserPlaylist> userPlaylists = new ArrayList<>();

    public List<UserPlaylist> getAllPlaylists() {
        return this.userPlaylists;
    }

    public void addNewPlaylist(String title) {
       userPlaylists.add(new UserPlaylist(title));
    }

    public UserPlaylist getSpecificPlaylist(String title) {
        return userPlaylists.stream().filter(userPlaylist -> userPlaylist.getTitle().equals(title)).findFirst().get();
    }
}
