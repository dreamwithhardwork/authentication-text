package com.automax.auth.services;

import javax.mail.MessagingException;

public interface EmailService {
    boolean sendOtp(String to) throws MessagingException;
    void sendEmail(String to,String subject,String body);
    boolean sendEmail(String to,String template);
}
