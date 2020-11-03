package com.automax.auth.controller;

import com.automax.auth.AuthenticationMessagingApplication;
import com.automax.auth.models.AuthenticationSuccess;
import com.automax.auth.models.LoginUser;
import com.automax.auth.models.PasswordReset;
import com.automax.auth.models.SignupUser;
import com.automax.auth.services.AuthenticationService;
import com.automax.auth.services.OtpService;
import org.models.core.dao.UsersRepository;
import org.models.core.enums.Roles;
import org.models.core.users.RegisteredUser;
import org.models.core.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RequestMapping(AuthenticationMessagingApplication.PATH+"/user")
@RestController
@CrossOrigin(origins = "*")
public class UsersController {


    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UsersRepository usersRepository;




    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccess> login(@RequestBody @Valid LoginUser user){
        String token = authenticationService.login(user);
        AuthenticationSuccess success=new AuthenticationSuccess(token);
        return ResponseEntity.ok(success);
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthenticationSuccess> signup(@RequestBody @Valid SignupUser user){
        String token = authenticationService.signUp(user.getRegisteredUser());
        AuthenticationSuccess success=new AuthenticationSuccess(token);
        return ResponseEntity.ok(success);
    }

    @PutMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid PasswordReset passwordReset){

     Boolean valid = authenticationService.passwordReset(passwordReset);
     return valid?ResponseEntity.ok("Password reset successful"):ResponseEntity.badRequest().body("Password reset failed");
    }


}
