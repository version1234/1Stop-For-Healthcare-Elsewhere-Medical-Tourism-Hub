package com.hackbright.capstone.controllers;

import com.hackbright.capstone.dtos.ProfileDto;
import com.hackbright.capstone.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/profile")
public class UserProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody ProfileDto profileDto){
        String passHash = passwordEncoder.encode(profileDto.getPassword());
        profileDto.setPassword(passHash);
        return profileService.addUser(profileDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody ProfileDto profileDto){
        System.out.println("test");
        return profileService.userLogin(profileDto);
    }

}
