package com.hackbright.capstone.dtos;

import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Policy;
import com.hackbright.capstone.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfirmPolityDto {

    private Long confirmid;

    private double price;

    private Date startDate;

    private Date endDate;

    private Long profileid;

    private Long policyid;

    private Profile profile;

    private Policy policy;


    public ConfirmPolityDto(Confirm confirm) {
        if (confirm.getConfirmid() != null) {
            this.confirmid = confirm.getConfirmid();
        }
        if (confirm.getPrice() != 0) {
            this.price = confirm.getPrice();
        }
        if (confirm.getStartDate() != null){
            this.startDate = confirm.getStartDate();
        }
        if (confirm.getEndDate() != null){
            this.endDate = confirm.getEndDate();
        }
        if (confirm.getPolicy() != null){
            this.policyid = confirm.getPolicy().getPolicyid();
             this.policy = confirm.getPolicy();
        }
        if (confirm.getProfile() != null){
            this.profileid = confirm.getProfile().getProfileid();
             this.profile = confirm.getProfile();
        }
    }
}
