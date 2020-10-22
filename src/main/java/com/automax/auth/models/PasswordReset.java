package com.automax.auth.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordReset {

    Integer otp;
    String username;
    String password;


}
