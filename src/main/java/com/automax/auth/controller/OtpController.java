package com.automax.auth.controller;

import com.automax.auth.services.EmailService;
import com.automax.auth.services.OtpService;
import com.automax.auth.services.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RequestMapping("/otp")
@RestController
public class OtpController {


    @Autowired
    EmailService emailService;

    @Autowired
    OtpService otpService;

    @Autowired
    TextMessage textMessage;



    @GetMapping("/send/email/{email}")
    public ResponseEntity<?> getOtp(@PathVariable @Valid @Email String email) throws MessagingException {
        boolean sent = emailService.sendOtp(email);
        if(sent)
            return ResponseEntity.ok("Sent");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }

    @GetMapping("/send/text/{mobile}")
    public ResponseEntity<?> sendOtp(@PathVariable  String mobile){
        boolean sent = textMessage.sendOtp(mobile);
        if(sent)
            return ResponseEntity.ok("Sent");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");

    }

    @GetMapping("/verify")
    public Boolean isValid(@RequestParam("from")  String from, @RequestParam("otp") Integer otp){
        return otpService.verifyOtp(from,otp);
    }
}
