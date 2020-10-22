package com.automax.auth.models;

import lombok.Getter;
import lombok.Setter;
import org.models.core.enums.Roles;
import org.models.core.users.RegisteredUser;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Arrays;


@Getter
@Setter
public class SignupUser {

    private String name;
    private String mobile;
    private String email;
    private String password;

    public RegisteredUser getRegisteredUser(){
             RegisteredUser registeredUser = new RegisteredUser();
             registeredUser.setRoles(Arrays.asList(Roles.ROLE_CUSTOMER));
             registeredUser.setName(name);
             registeredUser.setMobile(mobile);
             registeredUser.setEmail(email);
             registeredUser.setPassword(password);
             return registeredUser;
    }

}
