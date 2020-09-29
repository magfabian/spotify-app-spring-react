package com.codecool.spotify.model;

import lombok.*;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Playlist extends Card {

    @Builder(builderMethodName = "playlistBuilder")
    public Playlist(Long id, String spotifyId, String imageUrl, String onClickUrl, String header, String footer, String footerUrl) {
        super(id, spotifyId, imageUrl, onClickUrl, header, footer, footerUrl);
    }
}
