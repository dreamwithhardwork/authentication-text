package com.automax.auth.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationSuccess {

    public AuthenticationSuccess(){

    }

    String jwt;
    public AuthenticationSuccess(String jwt){
        this.jwt = jwt;
    }
}
