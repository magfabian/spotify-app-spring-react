package com.codecool.spotify.repository.user;

import com.codecool.spotify.model.user.SpotiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpotiUserRepository extends JpaRepository<SpotiUser, Long> {

    Optional<SpotiUser> findSpotiUserByEmailAddress(String emailAddress);

    // todo search by username or fullname
}
