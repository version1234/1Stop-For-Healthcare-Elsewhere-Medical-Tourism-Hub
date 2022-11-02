package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.ProfileDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ProfileService {
    @Transactional
    List<String> addUser(ProfileDto profileDto);

    List<String> userLogin(ProfileDto profileDto);

    ProfileDto userDetailsById(Long profileid);
}
