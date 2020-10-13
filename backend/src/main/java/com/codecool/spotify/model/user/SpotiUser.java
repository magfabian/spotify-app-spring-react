package com.codecool.spotify.model.user;

import com.codecool.spotify.model.favorite.Album;
import com.codecool.spotify.model.favorite.Artist;
import com.codecool.spotify.model.favorite.Playlist;
import com.codecool.spotify.model.favorite.Track;
import com.codecool.spotify.model.userPlaylist.UserPlaylist;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private String fullName;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "spotiUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<UserPlaylist> userPlaylists = new ArrayList<>();

    @OneToMany(mappedBy = "spotiUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Album> albums;

    @OneToMany(mappedBy = "spotiUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Artist> artists;

    @OneToMany(mappedBy = "spotiUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "spotiUser", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Track> tracks;
}
