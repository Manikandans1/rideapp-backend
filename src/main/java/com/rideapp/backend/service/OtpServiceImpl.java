package com.rideapp.backend.service;

import com.rideapp.backend.model.OtpEntity;
import com.rideapp.backend.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository repo;

    @Override
    public String sendOtp(String phone) {

        OtpEntity otp = new OtpEntity();
        otp.setPhone(phone);
        otp.setOtp("0000");

        repo.save(otp);

        return "0000";
    }

    @Override
    public boolean verifyOtp(String phone, String otp) {
        return repo.findByPhone(phone)
                .map(o -> o.getOtp().equals(otp))
                .orElse(false);
    }
}