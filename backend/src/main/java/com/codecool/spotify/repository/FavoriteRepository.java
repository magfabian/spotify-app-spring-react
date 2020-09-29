package com.codecool.spotify.repository;

import com.codecool.spotify.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


}
