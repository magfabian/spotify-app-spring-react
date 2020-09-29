package com.codecool.spotify.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String spotifyId;

    @Column
    private String imageUrl;

    @Column
    private String onClickUrl;

    @Column
    private String header;

    @Column
    private String footer;

    @Column
    private String footerUrl;

}
