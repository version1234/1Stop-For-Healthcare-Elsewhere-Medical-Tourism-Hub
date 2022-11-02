package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.ConfirmPolityDto;
import com.hackbright.capstone.dtos.ProfileDto;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.services.PolicyService;
import com.hackbright.capstone.services.ProfileService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/all")
    public List<Policy> getAllPolicies(){
        return policyService.findAll();
    }

    @GetMapping("/age/{ageValue}")
    public List<Policy> getPoliciesByAge(@PathVariable int ageValue){
        return policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(ageValue);
    }

//    @GetMapping("/{id}")
//    public Policy getPolicyById(@PathVariable Long id){
//        return policyService.findByid(id);
//    }

//    @PostMapping("/profile")   ///  need to work on this method.
//    public List<Policy> getPoliciesByProfile(@RequestBody ConfirmPolityDto confirmPolityDto){
//        System.out.println(confirmPolityDto.getProfileid());
//        ProfileDto profileDto =profileService.userDetailsById(confirmPolityDto.getProfileid());
//
//        return policyService.findByid(id);
//    }
}
