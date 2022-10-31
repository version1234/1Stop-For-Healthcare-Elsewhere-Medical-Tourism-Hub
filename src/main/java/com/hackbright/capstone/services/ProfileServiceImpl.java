package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.ProfileDto;
import com.hackbright.capstone.entities.Profile;
import com.hackbright.capstone.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(ProfileDto profileDto){
        List<String> response = new ArrayList<>();
        Profile profile = new Profile(profileDto);
        profileRepository.saveAndFlush(profile);
        response.add("User Added Successfully");
//        response.add("http://localhost:8080/login.html");
        return response;
    }


    @Override
    public List<String> userLogin(ProfileDto profileDto){
        List<String> response = new ArrayList<>();
        Optional<Profile> userOptional = profileRepository.findByUsername(profileDto.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(profileDto.getPassword(), userOptional.get().getPassword())) {
                response.add("http://localhost:8082/home.html");
                response.add(String.valueOf(userOptional.get().getProfileid()));
                response.add("User Login Successful");
            }else{
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }

}
