package com.hackbright.capstone.dtos;

import com.hackbright.capstone.entities.Confirm;
import com.hackbright.capstone.entities.Login;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class ConfirmDto implements Serializable {

    private Long confirmid;

    private double price;

    private Date startDate;

    private Date endDate;

    private Long profileid;

    private Long policyid;


    public ConfirmDto(Confirm confirm) {
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
//        if (confirm.getProfileid() != null){
//            this.profileid = confirm.getProfileid();
//        }
//        if (confirm.getPolicyid() != null){
//            this.policyid = confirm.getPolicyid();
//        }
    }
}
