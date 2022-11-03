package com.hackbright.capstone.services;

import com.hackbright.capstone.dtos.PolicyDto;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<PolicyDto> findAll() {
        List<Policy> policies = policyRepository.findAll();

        return convertPolicyToDto(policies);
    }

    @Override
    public Policy findByid(Long id) {
        Optional<Policy> policyOptional = policyRepository.findById(id);
        return policyOptional.get();
    }

    @Override
    public List<PolicyDto> findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(int age) {
        Optional<List<Policy>> policies = policyRepository.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(age, age);
        return convertPolicyToDto(policies.get());
    }

    public List<PolicyDto>  convertPolicyToDto(List<Policy> policies ){
        List<PolicyDto> policyDtoList = new ArrayList<PolicyDto>() ;
        for(Policy policy : policies){
            PolicyDto policyDto = new PolicyDto(policy);
            policyDtoList.add(policyDto);
        }
        return policyDtoList;
    }


}
