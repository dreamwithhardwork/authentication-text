package com.automax.auth.services;

import org.models.core.dao.OtpRepository;
import org.models.core.domain.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpRepository otpRepository;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Otp saveOtp(Otp otp) {
        otp.setOtp(bCryptPasswordEncoder.encode(otp.getOtp().toString()));
        return otpRepository.save(otp);
    }

    @Override
    public Boolean verifyOtp(String from, Integer otp) {
        Otp otpFrom = otpRepository.findFirstByUsername(from);
        Date now = new Date();
        Long expiryTime = (now.getTime() - otpFrom.getDate().getTime())/1000;
        return otpFrom!=null && otpFrom.getOtp().equals(otp) && expiryTime<50;
    }
}