package com.hackbright.capstone.repositories;

import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmRepository  extends JpaRepository<Confirm, Long> {

    List<Confirm> findByProfile(Profile profile);


}
