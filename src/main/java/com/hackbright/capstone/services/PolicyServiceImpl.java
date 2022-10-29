package com.hackbright.capstone.services;

import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

     @Override
     public List<Policy> findAll(){
         List<Policy> policies =  policyRepository.findAll();
         return policies;
     }

    @Override
    public Policy findByid(Long id){
        Optional<Policy> policyOptional =  policyRepository.findById(id);
        if(policyOptional.isPresent()){
            return policyOptional.get();
        }
        return null;
    }

    @Override
    public List<Policy> findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(int age){
        Optional<List<Policy>> policies =  policyRepository.findAllByAgelimitminLessThanAndAgelimitmaxGreaterThan(age,age);
        if(policies.isPresent())
            return policies.get();
        return  null;
    }

}
