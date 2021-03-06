package com.codecool.spotify.service;

import com.codecool.spotify.model.favorite.Track;
import com.codecool.spotify.model.user.SpotiUser;
import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import com.codecool.spotify.model.userPlaylist.UserPlaylistTrack;
import com.codecool.spotify.repository.favorite.FavoriteTrackRepository;
import com.codecool.spotify.repository.user.SpotiUserRepository;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistRepository;
import com.codecool.spotify.repository.userPlaylist.UserPlaylistTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class PlaylistService {

    @Autowired
    private SpotiUserRepository spotiUserRepository;

    @Autowired
    private UserPlaylistRepository userPlaylistRepository;

    @Autowired
    private UserPlaylistTrackRepository userPlaylistTrackRepository;
    
    @Autowired
    private FavoriteTrackRepository favoriteTrackRepository; 

    public List<UserPlaylist> getAllPlaylists(String email) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userPlaylistRepository.findAllBySpotiUser(spotiUser);
    }

    public void addNewPlaylist(String email, String title) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

       UserPlaylist userPlaylist = UserPlaylist.builder()
           .title(title)
           .total(0)
           .spotiUser(spotiUser)
           .build();
       userPlaylistRepository.save(userPlaylist);
    }

    public UserPlaylist getSpecificPlaylist(String email, String title) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userPlaylistRepository.findUserPlaylistByTitle(spotiUser, title);
    }

    public void addNewTrackToPlaylist(String email, String title, UserPlaylistTrack userPlaylistTrack) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserPlaylist userPlaylist = userPlaylistRepository.findUserPlaylistByTitle(spotiUser, title);
        Set<UserPlaylistTrack> userPlaylistTracks = userPlaylist.getUserPlaylistTracks();

        userPlaylistTrack.setFavorite(setFavorite(email, userPlaylistTrack));
        userPlaylistTracks.add(userPlaylistTrack);
        userPlaylistTrack.setUserPlaylist(userPlaylist);

        userPlaylist.setTotal(userPlaylistTracks.size());
        userPlaylist.setUserPlaylistTracks(userPlaylistTracks);
        userPlaylistRepository.save(userPlaylist);
    }

    public boolean setFavorite(String email, UserPlaylistTrack track) {
        boolean contains = false;

        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        List<Track> favoriteTracks = favoriteTrackRepository.findAllBySpotiUser(spotiUser);
        for (Track favoriteTrack : favoriteTracks) {
            if (favoriteTrack.getSpotifyId().equals(track.getSpotifyId())) {
                contains = true;
                break;
            }
        } return contains;
    }

    public void deleteTrackFromPlaylist(String email, Long playlistId, String spotifyId) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserPlaylist userPlaylist = userPlaylistRepository.findUserPlaylistById(spotiUser, playlistId);
        Set<UserPlaylistTrack> userPlaylistTracks = userPlaylist.getUserPlaylistTracks();

        UserPlaylistTrack userPlaylistTrackToDelete = userPlaylistTracks.stream().filter(track1 -> track1.getSpotifyId().equals(spotifyId)).findFirst().get();

        userPlaylistTracks.remove(userPlaylistTrackToDelete);

        userPlaylist.setUserPlaylistTracks(userPlaylistTracks);
        userPlaylist.setTotal(userPlaylistTracks.size());

        userPlaylistTrackRepository.deleteTrackFromPlaylist(playlistId, spotifyId);
        userPlaylistRepository.save(userPlaylist);
    }

    public void deletePlaylist(String email, UserPlaylist userPlaylist) {
        SpotiUser spotiUser = spotiUserRepository.findSpotiUserByEmailAddress(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userPlaylistRepository.deleteUserPlaylistById(spotiUser, userPlaylist.getId());

    }
}