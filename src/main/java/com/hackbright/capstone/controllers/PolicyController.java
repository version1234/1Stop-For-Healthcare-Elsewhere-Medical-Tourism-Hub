package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.ProfileDto;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @GetMapping("/all")
    public List<Policy> getAllPolicies(){
        return policyService.findAll();
    }

    @GetMapping("/age/{ageValue}")
    public List<Policy> getPoliciesByAge(@PathVariable int ageValue){
        return policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(ageValue);
    }

    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id){
        return policyService.findByid(id);
    }
}
