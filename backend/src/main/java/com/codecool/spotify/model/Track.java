package com.codecool.spotify.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
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

    @Transient
    private boolean favorite;
}
