package com.codecool.spotify.repository;

import com.codecool.spotify.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteArtistRepository extends JpaRepository<Artist,Long> {
}
