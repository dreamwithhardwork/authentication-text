package com.automax.auth.services;


import org.models.core.domain.Otp;

public interface OtpService {
    public Otp saveOtp(Otp otp);
    public Boolean verifyOtp(String from, Integer otp);
}
