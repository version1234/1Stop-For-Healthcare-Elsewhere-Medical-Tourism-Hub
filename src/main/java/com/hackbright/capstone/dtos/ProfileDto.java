package com.hackbright.capstone.dtos;

import com.hackbright.capstone.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto implements Serializable {


    private Long profileid;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String dateofbirth;

    private String email;

    private String phonenumber;

    private String address;

    private String city;

    private String state;

    private String zip;

    public ProfileDto(Profile profile) {
        if (profile.getProfileid() != null)
            this.profileid = profile.getProfileid();
        if(profile.getUsername()!= null)
            this .username = profile.getUsername();
        if(profile.getPassword()!= null)
            this .password = profile.getPassword();
        if (profile.getFirstname() != null)
            this.firstname = profile.getFirstname();
        if (profile.getLastname() != null)
            this.lastname = profile.getLastname();
        if (profile.getDateofbirth() != null)
            this.dateofbirth = profile.getDateofbirth();
        if (profile.getEmail() != null)
            this.email = profile.getEmail();
        if (profile.getPhonenumber() != null)
            this.phonenumber = profile.getPhonenumber();
        if (profile.getAddress() != null)
            this.address = profile.getAddress();
        if (profile.getCity() != null)
            this.city = profile.getCity();
        if (profile.getState() != null)
            this.state = profile.getState();
        if (profile.getZip() != null)
            this.zip = profile.getZip();
    }
}
