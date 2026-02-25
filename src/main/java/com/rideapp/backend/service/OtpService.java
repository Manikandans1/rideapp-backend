package com.rideapp.backend.service;

public interface OtpService {

    String sendOtp(String phone);

    boolean verifyOtp(String phone, String otp);

}