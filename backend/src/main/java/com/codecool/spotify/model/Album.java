package com.codecool.spotify.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Album {
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

    private boolean favorite;
}
