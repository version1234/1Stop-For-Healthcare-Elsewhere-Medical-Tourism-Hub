package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.*;
import com.hackbright.capstone.email.EmailService;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.services.ConfirmService;
import com.hackbright.capstone.services.PolicyService;
import com.hackbright.capstone.services.ProfileService;
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

    @Autowired
    private EmailService emailService;

    @GetMapping("/all")
    public List<PolicyDto> getAllPolicies() {
        return policyService.findAll();
    }

    @GetMapping("/age/{ageValue}")
    public List<PolicyDto> getPoliciesByAge(@PathVariable int ageValue) {
        return policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(ageValue);
    }

    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return policyService.findByid(id);
    }

    @PostMapping("/profile")   ///  need to work on this method.
    public List<PolicyDto> getPoliciesByProfile(@RequestBody FindPolicyDto findPolicyDto) {
        //Get
        ProfileDto profileDto = profileService.userDetailsById(findPolicyDto.getProfileid());
        int years = 0;
        int selectedDays = 0;
        try {
            Date today = new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.s");
            Date dob = formatter.parse(profileDto.getDateofbirth().toString());

            // Calculate the age of the user
            int days = calculateDays(today, dob);
            years = days / 365;

            selectedDays = calculateDays(findPolicyDto.getStartDate(), findPolicyDto.getEndDate());
        } catch (Exception exception) {
        }

        //Get all available policies corresponding to user's aga
        List<PolicyDto> policyDtoList = policyService.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(years);

        for (PolicyDto policyDto : policyDtoList) {
            // calculate Premium and update the Price
            policyDto.setPolicydailyprice(policyDto.getPolicydailyprice() * selectedDays);
        }

        return policyDtoList;
    }

    @PostMapping("/save")
    public List<String> savePolicy(@RequestBody FindPolicyDto findPolicyDto) {

        ConfirmDto confirmDto = new ConfirmDto();
        confirmDto.setProfileid(findPolicyDto.getProfileid());
        confirmDto.setPolicyid(findPolicyDto.getPolicyid());
        confirmDto.setStartDate(findPolicyDto.getStartDate());
        confirmDto.setEndDate((findPolicyDto.getEndDate()));

        int days = calculateDays(confirmDto.getStartDate(), confirmDto.getEndDate());
        Policy policy = policyService.findByid(findPolicyDto.getPolicyid());

        confirmDto.setPrice(days * policy.getPolicydailyprice());


        ProfileDto profileDto = profileService.userDetailsById(findPolicyDto.getProfileid());


        sendEmailToUser(profileDto, confirmDto, policy);

        return confirmService.saveConfirmation(confirmDto);
    }


    public int calculateDays(Date firstDate, Date SecondDate) {
        long diffInMillies = Math.abs(SecondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diff;
    }

    @PostMapping("/email")
    public void sendEmail(@RequestBody MessageDto message) {
        System.out.println(message);
        emailService.sendSimpleMessage(message);

    }


    public void sendEmailToUser(ProfileDto profileDto, ConfirmDto confirmDto, Policy policy) {

        String body = "<html><Body> " + profileDto.getFirstname() + " " + profileDto.getLastname() + ", <br><br>";
        body += "Thank you for choosing the policy <Br>";
        body += " Your Police id number is : " + policy.getPolicyid();
        body += "<br> Polciy name is :" + policy.getPolicyname();
        body += "<br> Policy details are : " + policy.getPolicydetail();
        body += "<br> policy limit is :" + policy.getPolicylimit();
        body += "<br> poliicy Amount for you selected period is : $" + confirmDto.getPrice();
        body += "<br> for the period from :" + confirmDto.getStartDate() + "  to : " + confirmDto.getEndDate();
        body += "<br><br> Thank you";
        body += "<br> 1st Stop Healthcare Elsewhere Medical Tourism Plan";
        body += "</body></html>";


        MessageDto messageDto = new MessageDto();
        messageDto.setTo(profileDto.getEmail());
        messageDto.setSubject("1Stop Healthcare Policy Confirmation");
        messageDto.setBody(body);
//        emailService.sendSimpleMessage(messageDto);

        emailService.sendHtmlMessage(messageDto);

    }
}


