package com.codecool.spotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany
    private Set<Track> tracks;

//    public void addNewTrackToPlaylist(Card card) {
//        boolean contains = false;
//        for (Card track:tracks) {
//            if (track.getImageUrl().equals(card.getImageUrl())) {
//                contains = true;
//                break;
//            }
//        }
//        if (!contains) {
//            tracks.add(card);
//            total ++;
//        }
//    }
}
