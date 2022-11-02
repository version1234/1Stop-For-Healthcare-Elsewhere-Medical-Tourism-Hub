package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.ConfirmDto;
import com.hackbright.capstone.dtos.ConfirmPolityDto;
import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.entities.Profile;
import com.hackbright.capstone.repositories.ConfirmRepository;
import com.hackbright.capstone.repositories.PolicyRepository;
import com.hackbright.capstone.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private ConfirmRepository confirmRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<ConfirmDto> findAll() {
        List<Confirm> confirms = confirmRepository.findAll();
        List<ConfirmDto> confirmDtos = convertConfirmsToDto(confirms);
        return confirmDtos;
    }

    @Override
    public List<ConfirmDto> findByProfile(Long profileid) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileid);
        Profile profile = null;
        if (optionalProfile.isPresent())
            profile = optionalProfile.get();

        List<Confirm> confirms = confirmRepository.findByProfile(profile);
        List<ConfirmDto> confirmDtos = convertConfirmsToDto(confirms);
        return confirmDtos;

    }

    @Override
    public List<ConfirmPolityDto> getConfirmsByProfile(Long profileid) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileid);
        Profile profile = null;
        if (optionalProfile.isPresent())
            profile = optionalProfile.get();

        List<Confirm> confirms = confirmRepository.findByProfile(profile);
        return  convertConfirmsToConfirmPolicyDto(confirms);

    }
    @Override
    public ConfirmDto findById(Long id) {
        ConfirmDto confirmDto = new ConfirmDto();
        Optional<Confirm> confirmOptional = confirmRepository.findById(id);
        if (confirmOptional.isPresent()) {
            Confirm confirm = confirmOptional.get();
            confirmDto = new ConfirmDto(confirm);
        }
        return confirmDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        confirmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<String> saveConfirmation(ConfirmDto confirmDto) {
        List<String> response = new ArrayList<>();
        Confirm confirm = new Confirm(confirmDto);
        Optional<Policy> optionalPolicy = policyRepository.findById(confirmDto.getPolicyid());
        Policy policy = null;
        if (optionalPolicy.isPresent())
            policy = optionalPolicy.get();

        confirm.setPolicy(policy);
        Optional<Profile> optionalProfile = profileRepository.findById(confirmDto.getProfileid());
        Profile profile = null;
        if (optionalProfile.isPresent())
            profile = optionalProfile.get();
        confirm.setProfile(profile);
        confirmRepository.saveAndFlush(confirm);
        response.add("User Added Successfully");
//        response.add("http://localhost:8080/login.html");
        return response;
    }

        private List<ConfirmDto> convertConfirmsToDto(List<Confirm> confirms){
            List<ConfirmDto> confirmDtos = new ArrayList<ConfirmDto>();
            if(confirms!= null) {
                for (Confirm confirm:confirms) {
                    confirmDtos.add(new ConfirmDto(confirm));
                }
            }
            return confirmDtos;

        }

    private List<ConfirmPolityDto> convertConfirmsToConfirmPolicyDto(List<Confirm> confirms){
        List<ConfirmPolityDto> confirmPolityDtos = new ArrayList<ConfirmPolityDto>();
        if(confirms!= null) {
            for (Confirm confirm:confirms) {
                confirmPolityDtos.add(new ConfirmPolityDto(confirm));
            }
        }
        return confirmPolityDtos;

    }

}
