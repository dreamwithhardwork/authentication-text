package com.automax.auth.controller;

import com.automax.auth.models.AuthenticationSuccess;
import com.automax.auth.models.LoginUser;
import com.automax.auth.services.AuthenticationService;
import org.models.core.dao.UsersRepository;
import org.models.core.users.RegisteredUser;
import org.models.core.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/auth")
@RestController
public class UsersController {


    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UsersRepository usersRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccess> login(@RequestBody @Valid LoginUser user){

        List<RegisteredUser> users = usersRepository.findAll();
        String token = authenticationService.login(user);
        AuthenticationSuccess success=new AuthenticationSuccess(token);
        return ResponseEntity.ok(success);

    }


    @PostMapping("/signup")
    public ResponseEntity<AuthenticationSuccess> signup(@RequestBody @Valid RegisteredUser user){
        String token = authenticationService.signUp(user);
        AuthenticationSuccess success=new AuthenticationSuccess(token);
        return ResponseEntity.ok(success);
    }
}
