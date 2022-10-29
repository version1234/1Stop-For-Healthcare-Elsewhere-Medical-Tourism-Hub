package com.hackbright.capstone.dtos;

import com.hackbright.capstone.entities.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDto implements Serializable {

    private Long policyid;

    private String policyname;

    private String policydetail;

    private String policylimit;

    private String policydailyprice;

    private int agelimitmin;

    private int agelimitmax;

    public PolicyDto(Policy policy) {
        if (policy.getPolicyid() != null)
            this.policyid = policy.getPolicyid();
        if (policy.getPolicyname() != null)
            this.policyname = policy.getPolicyname();
        if (policy.getPolicydetail() != null)
            this.policydetail = policy.getPolicydetail();
        if (policy.getPolicylimit() != null)
            this.policylimit = policy.getPolicylimit();
        if (policy.getPolicydailyprice() != null)
            this.policydailyprice = policy.getPolicydailyprice();
        if (policy.getAgelimitmin() != 0)
            this.agelimitmin = policy.getAgelimitmin();
        if (policy.getAgelimitmax() != 0)
            this.agelimitmax = policy.getAgelimitmax();
    }
}
