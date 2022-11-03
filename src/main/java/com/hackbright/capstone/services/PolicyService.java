package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.PolicyDto;
import com.hackbright.capstone.entities.Policy;

import java.util.List;

public interface PolicyService {
    List<PolicyDto> findAll();

    Policy findByid(Long id);

    List<PolicyDto> findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(int age);
}
