package com.hackbright.capstone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "confirm")
@Setter   //Added Getter and Setter from lombok annotation
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Confirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long confirmid;

    @Column
    private double price;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

//    @Column
//    private Long profileid;


    @ManyToOne
    @JoinColumn(name="policyid", nullable = false)
    @JsonBackReference
    private Policy policy;

    @ManyToOne
    @JoinColumn(name="profileid", nullable = false)
    @JsonBackReference
    private Profile profile;


}
