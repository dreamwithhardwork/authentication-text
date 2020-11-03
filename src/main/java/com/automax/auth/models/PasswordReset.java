package com.automax.auth.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordReset {

    String otp;
    String username;
    String password;


}
