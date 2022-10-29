package com.hackbright.capstone.dtos;

import com.hackbright.capstone.entities.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto  implements Serializable {

    private Long loginid;

    private String username;

    private String password;

    public LoginDto(Login login) {
        if (login.getLoginid() != null) {
            this.loginid = login.getLoginid();
        }
        if (login.getUsername() != null) {
            this.username = login.getUsername();
        }
        if (login.getPassword() != null){
            this.password = login.getPassword();
        }
    }
}
