package com.hackbright.capstone.services;

import com.hackbright.capstone.entities.Policy;

import java.util.List;

public interface PolicyService {
    List<Policy> findAll();

    Policy findByid(Long id);

    List<Policy> findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(int age);
}
