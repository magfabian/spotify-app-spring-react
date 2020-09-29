package com.codecool.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @Column
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
