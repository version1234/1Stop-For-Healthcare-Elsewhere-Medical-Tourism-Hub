package com.hackbright.capstone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackbright.capstone.dtos.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profile")
@Setter   //Added Getter and Setter from lombok annotation
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileid;

    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String dateofbirth;

    @Column
    private String email;

    @Column
    private String phonenumber;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zip;

//    @OneToOne
//    @JoinColumn(name="loginid", nullable = false)
//    @JsonBackReference
//    private Login login;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Confirm> confirmSet = new HashSet<>();


    public Profile(ProfileDto profileDto) {
        if (profileDto.getProfileid() != null)
            this.profileid = profileDto.getProfileid();
        if (profileDto.getUsername() != null)
            this.username = profileDto.getUsername();
        if (profileDto.getPassword() != null)
            this.password = profileDto.getPassword();
        if (profileDto.getFirstname() != null)
            this.firstname = profileDto.getFirstname();
        if (profileDto.getLastname() != null)
            this.lastname = profileDto.getLastname();
        if (profileDto.getDateofbirth() != null)
            this.dateofbirth = profileDto.getDateofbirth();
        if (profileDto.getEmail() != null)
            this.email = profileDto.getEmail();
        if (profileDto.getPhonenumber() != null)
            this.phonenumber = profileDto.getPhonenumber();
        if (profileDto.getAddress() != null)
            this.address = profileDto.getAddress();
        if (profileDto.getCity() != null)
            this.city = profileDto.getCity();
        if (profileDto.getState() != null)
            this.state = profileDto.getState();
        if (profileDto.getZip() != null)
            this.zip = profileDto.getZip();

    }

}
