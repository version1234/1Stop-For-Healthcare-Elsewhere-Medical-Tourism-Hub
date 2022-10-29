package com.hackbright.capstone.repositories;

import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyRepository  extends JpaRepository<Policy, Long> {

    Optional<List<Policy>> findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(int agelimitmin, int agelimitmax);
}
