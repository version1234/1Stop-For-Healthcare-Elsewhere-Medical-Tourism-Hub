package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.*;
import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.entities.Profile;
import com.hackbright.capstone.services.ConfirmService;
import com.hackbright.capstone.services.PolicyService;
import com.hackbright.capstone.services.ProfileService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/all")
    public List<PolicyDto> getAllPolicies()
    {
        return policyService.findAll();
    }

    @GetMapping("/age/{ageValue}")
    public List<PolicyDto> getPoliciesByAge(@PathVariable int ageValue){
        return policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(ageValue);
    }

    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id){
        return policyService.findByid(id);
    }

    @PostMapping("/profile")   ///  need to work on this method.
    public List<PolicyDto> getPoliciesByProfile(@RequestBody FindPolicyDto findPolicyDto){
        //Get
        ProfileDto profileDto =profileService.userDetailsById(findPolicyDto.getProfileid());
        long years=0;
        long selectedDays=0;
        try {
            Date today = new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.s");
            Date dob = formatter.parse(profileDto.getDateofbirth().toString());
            System.out.println(dob);

            long diffInMillies = Math.abs(dob.getTime() - today.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            years = diff/365;

            long diffInMillies1 = Math.abs(findPolicyDto.getEndDate().getTime() - findPolicyDto.getStartDate().getTime());
             selectedDays = TimeUnit.DAYS.convert(diffInMillies1, TimeUnit.MILLISECONDS);
        }catch(Exception exception){}


        List<PolicyDto> policyDtoList = policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan((int) years);

        for(PolicyDto policyDto : policyDtoList){
            policyDto.setPolicydailyprice(policyDto.getPolicydailyprice() * (int)selectedDays);
        }

        return policyDtoList;
    }

    @PostMapping("/save")
    public List<String>  savePolicy(@RequestBody FindPolicyDto findPolicyDto){

        ConfirmDto confirmDto = new ConfirmDto();
        confirmDto.setProfileid(findPolicyDto.getProfileid());
        confirmDto.setPolicyid(findPolicyDto.getPolicyid());
        confirmDto.setStartDate(findPolicyDto.getStartDate());
        confirmDto.setEndDate((findPolicyDto.getEndDate()));
        confirmDto.setPrice(findPolicyDto.getPrice());

//        confirmService.saveConfirmation(confirmDto);
        return confirmService.saveConfirmation(confirmDto);
    }
}
