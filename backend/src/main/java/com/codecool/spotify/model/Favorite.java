package com.codecool.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {

    private List<Artist> artists;

    private List<Album> albums;

    private List<Track> tracks;

    private List<Playlist> playlists;
}
