package com.rideapp.backend.controller;

import com.rideapp.backend.dto.BaseResponse;
import com.rideapp.backend.dto.OtpRequest;
import com.rideapp.backend.dto.OtpVerifyRequest;
import com.rideapp.backend.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final OtpService otpService;
//
//    @PostMapping("/send-otp")
//    public BaseResponse<String> sendOtp(@RequestBody OtpRequest request){
//
//        String otp = otpService.sendOtp(request.getPhone());
//
//        return new BaseResponse<>(
//                true,
//                "OTP sent successfully",
//                otp
//        );
//    }
//
//    @PostMapping("/verify-otp")
//    public BaseResponse<String> verifyOtp(@RequestBody OtpVerifyRequest request){
//
//        boolean valid = otpService.verifyOtp(
//                request.getPhone(),
//                request.getOtp()
//        );
//
//        if(!valid)
//            throw new RuntimeException("Invalid OTP");
//
//        return new BaseResponse<>(
//                true,
//                "Login successful",
//                "VERIFIED"
//        );
//    }
//}

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OtpService otpService;

    @PostMapping("/send-otp")
    public BaseResponse<String> sendOtp(@RequestParam String phone) {
        otpService.sendOtp(phone);
        return new BaseResponse<>(true, "OTP Sent", null);
    }

    @PostMapping("/verify-otp")
    public BaseResponse<String> verifyOtp(
            @RequestParam String phone,
            @RequestParam String otp) {

        boolean valid = otpService.verifyOtp(phone, otp);

        if (!valid) {
            return new BaseResponse<>(false, "Invalid OTP", null);
        }

        return new BaseResponse<>(true, "OTP Verified", null);
    }
}