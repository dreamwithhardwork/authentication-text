package com.automax.auth.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
    private String username;
    private String password;
    private String otp;
    private LoginType loginType;
}
