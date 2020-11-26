package com.automax.auth.services;


import org.models.core.domain.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    OtpService otpService;

    @Override
    public boolean sendOtp(String to) throws MessagingException {
        Otp otp = new Otp();
        otp.setDate(new Date());
        otp.setUsername(to);
        Integer otpn = (int) Math.round(Math.random()*100000);
        otp.setOtp(otpn.toString());
        Otp res = otpService.saveOtp(otp);
        MimeMessage message =  javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("OTP for autoride");
        message.setContent("<div><p>Hello,</p></br> <p>Your login OTP for autoride is </p> <h4>"+otpn+"</h4></div>","text/html");
        javaMailSender.send(message);
        return res.getId()!=null;
    }



    @Override
    @Async
    public void sendEmail(String to, String subject, String body) {
        MimeMessage message =  javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            message.setContent("<div><p>Hello ,</p></br> <p>" + body+ "</p></div>","text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
        return;
    }

    @Override
    public boolean sendEmail(String to, String template) {
        return false;
    }
}
