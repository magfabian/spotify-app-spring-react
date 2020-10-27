package com.codecool.spotify.model.userPlaylist;

import com.codecool.spotify.model.user.SpotiUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
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

    private int total;

    @OneToMany(mappedBy = "userPlaylist", cascade = CascadeType.ALL)
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<UserPlaylistTrack> userPlaylistTracks = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private SpotiUser spotiUser;


}
