package com.codecool.spotify.model;

import lombok.*;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Album extends Card {

    @Builder(builderMethodName = "albumBuilder")
    public Album(Long id, String spotifyId, String imageUrl, String onClickUrl, String header, String footer, String footerUrl) {
        super(id, spotifyId, imageUrl, onClickUrl, header, footer, footerUrl);
    }
}
