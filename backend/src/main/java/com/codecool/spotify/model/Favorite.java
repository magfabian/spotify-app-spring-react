package com.codecool.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<Artist> artists;

    @OneToMany
    private Set<Album> albums;

    @OneToMany
    private Set<Track> tracks;

    @OneToMany
    private Set<Playlist> playlists;
}
