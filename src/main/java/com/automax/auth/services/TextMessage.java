package com.automax.auth.services;

public interface TextMessage {
    boolean sendOtp(String to);
    boolean sendMessage(String to, String subject);
}
