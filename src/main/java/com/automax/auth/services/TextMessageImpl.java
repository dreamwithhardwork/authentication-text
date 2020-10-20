package com.automax.auth.services;


import org.models.core.dao.OtpRepository;
import org.models.core.domain.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class TextMessageImpl implements TextMessage {


    @Autowired
    RestTemplate restTemplate;

    @Value("${token}")
    String api_token;

    @Autowired
    OtpService otpService;

    @Override
    public boolean sendOtp(String to) {
        Integer code = (int) Math.round(Math.random()*100000);
        String url = "https://portal.mobtexting.com/api/v2/sms/send?access_token="+api_token+"&message=Your login OTP for automax is "+code+"&sender=TXTSMS&to=91"+to+"&service=T";
        Otp otp = new Otp();
        otp.setDate(new Date());
        otp.setUsername(to);
        otp.setOtp(code.toString());
        otpService.saveOtp(otp);
        ResponseEntity<String> res = restTemplate.getForEntity(url,String.class);
        return true;
    }
}
