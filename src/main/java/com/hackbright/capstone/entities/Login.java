package com.hackbright.capstone.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackbright.capstone.dtos.LoginDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Login")
@Setter   //Added Getter and Setter from lombok annotation
@Getter
public class Login {

    public Login(Long loginid, String username, String password) {
        this.loginid = loginid;
        this.username = username;
        this.password = password;
    }

    public Login(LoginDto loginDto) {
        if (loginDto.getUsername() != null) {
            this.username = loginDto.getUsername();
        }
        if (loginDto.getPassword() != null) {
            this.password = loginDto.getPassword();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginid;

    @Column(unique = true)
    private String username;

    @Column
    private String password;


//    @OneToOne(mappedBy = "login", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = “loginid”, unique = true)
//    @JsonManagedReference
//    private Profile profile;


}
