package com.automax.auth.services;

import com.automax.auth.JwtTokenUtil;
import com.automax.auth.models.LoginType;
import com.automax.auth.models.LoginUser;
import com.automax.auth.provider.OtpAuthentication;
import com.automax.auth.provider.UsernamePasswordAuthentication;
import org.models.core.dao.UsersRepository;
import org.models.core.users.RegisteredUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public String login(LoginUser loginUser){
        Authentication authentication;
        RegisteredUser user = new RegisteredUser();

        try{
            logger.debug("debug");
            authentication = getAuthentication(loginUser);
        }catch (BadCredentialsException ex){
            throw new BadCredentialsException(loginUser.getUsername());
        }
        catch (Exception ex){
            throw new BadCredentialsException(ex.getMessage());
        }
        return generateToken((RegisteredUser) authentication.getDetails());
    }

    public String signUp(RegisteredUser user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = usersRepository.save(user);
        return  generateToken(user);
    }

    private Authentication getAuthentication(LoginUser user) {

        if(user.getLoginType()== LoginType.OTP) {
            return authenticationManager.authenticate(new OtpAuthentication(user.getUsername(),user.getOtp()));
        }
        else {
           return authenticationManager.authenticate(new UsernamePasswordAuthentication(user.getUsername(),user.getPassword()));
        }
    }

    String generateToken(RegisteredUser userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }



}
