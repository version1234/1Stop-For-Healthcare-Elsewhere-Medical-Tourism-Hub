package com.hackbright.capstone.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackbright.capstone.dtos.PolicyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "policy")
@Setter   //Added Getter and Setter from lombok annotation
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyid;

    @Column
    private String policyname;

    @Column
    private String policydetail;

    @Column
    private String policylimit;

    @Column
    private int policydailyprice;

    @Column
    private int agelimitmin;

    @Column
    private int agelimitmax;


    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Confirm> confirmSet = new HashSet<>();


    public Policy(PolicyDto policyDto) {
        if (policyDto.getPolicyid() != null)
            this.policyid = policyDto.getPolicyid();
        if (policyDto.getPolicyname() != null)
            this.policyname = policyDto.getPolicyname();
        if (policyDto.getPolicydetail() != null)
            this.policydetail = policyDto.getPolicydetail();
        if (policyDto.getPolicylimit() != null)
            this.policylimit = policyDto.getPolicylimit();
        if (policyDto.getPolicydailyprice() != 0)
            this.policydailyprice = policyDto.getPolicydailyprice();
        if (policyDto.getAgelimitmin() != 0)
            this.agelimitmin = policyDto.getAgelimitmin();
        if (policyDto.getAgelimitmax() != 0)
            this.agelimitmax = policyDto.getAgelimitmax();
    }
}
