package com.codecool.spotify.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    private String spotifyId;
    private String imageUrl;
    private String onClickUrl;
    private String header;
    private String footer;
    private String footerUrl;
}
