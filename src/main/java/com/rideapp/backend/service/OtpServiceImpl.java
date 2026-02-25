package com.rideapp.backend.service;

import com.rideapp.backend.model.OtpEntity;
import com.rideapp.backend.repository.OtpRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository repo;

    @Value("${twilio.phone.number}")
    private String twilioNumber;

    private String generateOtp() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

    private String normalizePhone(String phone) {

        phone = phone.trim();
        phone = phone.replaceAll("\\s+", "");

        if (!phone.startsWith("+91")) {

            if (phone.startsWith("91")) {
                phone = "+" + phone;
            } else if (phone.length() == 10) {
                phone = "+91" + phone;
            }
        }

        return phone;
    }

    @Override
    public String sendOtp(String phone) {

        phone = normalizePhone(phone);

        String otpCode = generateOtp();

        Optional<OtpEntity> existing = repo.findByPhone(phone);

        OtpEntity otp;

        if (existing.isPresent()) {
            otp = existing.get();
            otp.setOtp(otpCode);
        } else {
            otp = new OtpEntity();
            otp.setPhone(phone);
            otp.setOtp(otpCode);
        }

        repo.save(otp);

        // 🔥 SEND SMS USING TWILIO
        Message.creator(
                new PhoneNumber(phone),
                new PhoneNumber(twilioNumber),
                "Your RideApp OTP is: " + otpCode
        ).create();

        return "OTP Sent";
    }

    @Override
    public boolean verifyOtp(String phone, String otp) {

        phone = normalizePhone(phone);

        return repo.findByPhone(phone)
                .map(o -> o.getOtp().equals(otp))
                .orElse(false);
    }
}