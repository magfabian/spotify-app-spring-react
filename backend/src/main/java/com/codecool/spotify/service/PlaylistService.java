package com.codecool.spotify.service;

import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistRepository;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistService {

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;

    @Autowired
    private UserPlaylistTrackRepository userPlaylistTrackRepository;

    public List<UserPlaylist> getAllPlaylists() {
        return userPlaylistRepository.findAll();
    }

    public void addNewPlaylist(String title) {
       UserPlaylist userPlaylist = UserPlaylist.builder()
               .title(title)
               .total(0)
               .build();
       userPlaylistRepository.save(userPlaylist);
    }

    public UserPlaylist getSpecificPlaylist(String title) {
        return userPlaylistRepository.findUserPlaylistByTitle(title);
    }

    public void addNewTrackToPlaylist(String title, UserPlaylistTrack userPlaylistTrack) {
        UserPlaylist userPlaylist = userPlaylistRepository.findUserPlaylistByTitle(title);
        Set<UserPlaylistTrack> userPlaylistTracks = userPlaylist.getUserPlaylistTracks();

        userPlaylistTracks.add(userPlaylistTrack);
        userPlaylistTrack.setUserPlaylist(userPlaylist);

        userPlaylist.setTotal(userPlaylistTracks.size());
        userPlaylist.setUserPlaylistTracks(userPlaylistTracks);
        userPlaylistRepository.save(userPlaylist);
    }

    public void deleteTrackFromPlaylist(Long id, UserPlaylistTrack track) {
        UserPlaylist userPlaylist = userPlaylistRepository.findUserPlaylistById(id);
        Set<UserPlaylistTrack> userPlaylistTracks = userPlaylist.getUserPlaylistTracks();

        UserPlaylistTrack userPlaylistTrackToDelete = userPlaylistTracks.stream().filter(track1 -> track1.getSpotifyId().equals(track.getSpotifyId())).findFirst().get();

        userPlaylistTracks.remove(userPlaylistTrackToDelete);

        userPlaylist.setUserPlaylistTracks(userPlaylistTracks);
        userPlaylist.setTotal(userPlaylistTracks.size());

        userPlaylistTrackRepository.deleteTrackFromPlaylist(id, track.getSpotifyId());
        userPlaylistRepository.save(userPlaylist);
    }

    public void deletePlaylist(UserPlaylist userPlaylist) {

        userPlaylistRepository.deleteUserPlaylistById(userPlaylist.getId());
    }

    public UserPlaylist editPlaylistTitle(UserPlaylist userPlaylist) {
        UserPlaylist playlist = userPlaylistRepository.findUserPlaylistById(userPlaylist.getId());
        return userPlaylistRepository.updateUserPlaylistTitle(playlist.getId(), userPlaylist.getTitle());
    }
}