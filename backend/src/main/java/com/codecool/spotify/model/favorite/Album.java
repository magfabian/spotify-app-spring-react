package com.codecool.spotify.model.favorite;

import com.codecool.spotify.model.user.SpotiUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column
    private boolean favorite;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private SpotiUser spotiUser;
}
