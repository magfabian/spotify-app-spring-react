package com.codecool.spotify.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPlaylist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Transient
    private int total;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Singular
    private Set<Track> tracks;

}
