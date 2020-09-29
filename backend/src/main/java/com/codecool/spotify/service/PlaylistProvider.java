package com.codecool.spotify.service;

import com.codecool.spotify.model.Track;
import com.codecool.spotify.model.UserPlaylist;
import com.codecool.spotify.repository.UserPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PlaylistProvider {

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;

    public List<UserPlaylist> getAllPlaylists() {
        return userPlaylistRepository.findAll();
    }

    public void addNewPlaylist(String title) {
       UserPlaylist userPlaylist = UserPlaylist.builder()
               .title(title)
               .total(0)
               .tracks(new HashSet<>())
               .build();
       userPlaylistRepository.save(userPlaylist);
    }

    public UserPlaylist getSpecificPlaylist(String title) {
        return userPlaylistRepository.findUserPlaylistByTitle(title);
    }

    public void addNewTrackToPlaylist(String title, Track track) {
        UserPlaylist userPlaylist = userPlaylistRepository.findUserPlaylistByTitle(title);
        userPlaylist.getTracks().add(track);
        userPlaylistRepository.save(userPlaylist);
    }
}
