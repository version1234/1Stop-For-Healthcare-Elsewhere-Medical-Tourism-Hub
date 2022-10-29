package com.hackbright.capstone.repositories;

import com.hackbright.capstone.entities.Login;
import com.hackbright.capstone.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUsername(String username);
}
