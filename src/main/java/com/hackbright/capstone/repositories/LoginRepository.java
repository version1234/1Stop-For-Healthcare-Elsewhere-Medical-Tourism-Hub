package com.hackbright.capstone.repositories;

import com.hackbright.capstone.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>  {

    Optional<Login> findByUsername(String username);

}
