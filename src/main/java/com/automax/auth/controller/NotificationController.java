package com.automax.auth.controller;

import com.automax.auth.AuthenticationMessagingApplication;
import com.automax.auth.services.EmailService;
import com.automax.auth.services.OtpService;
import com.automax.auth.services.TextMessage;
import org.models.core.dao.UsersRepository;
import org.models.core.users.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthenticationMessagingApplication.PATH+"/notification")
@RestController
@CrossOrigin(origins = "*")
public class NotificationController {
    @Autowired
    EmailService emailService;

    @Autowired
    TextMessage textMessage;


    @GetMapping("/notify")
    public Boolean sendOtp(@RequestParam("mobile") String mobile,@RequestParam("email") String email,
                                     @RequestParam("subject") String subject,@RequestParam("body") String body){
        boolean sent = textMessage.sendMessage(mobile,body);
         emailService.sendEmail(email,subject,body);
         return  true;

    }

}
