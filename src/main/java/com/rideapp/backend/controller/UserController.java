package com.rideapp.backend.controller;

import com.rideapp.backend.dto.BaseResponse;
import com.rideapp.backend.dto.UserRoleRequest;
import com.rideapp.backend.model.UserEntity;
import com.rideapp.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save-role")
    public BaseResponse<UserEntity> saveRole(
            @RequestBody UserEntity user) {

        try {
            return new BaseResponse<>(
                    true,
                    "Role saved",
                    userService.saveRole(user)
            );
        } catch (Exception e) {
            return new BaseResponse<>(
                    false,
                    e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/by-phone")
    public BaseResponse<UserEntity> getUser(@RequestParam String phone) {

        UserEntity user = userService.getUser(phone);

        if (user == null) {
            return new BaseResponse<>(
                    false,
                    "User not found",
                    null
            );
        }

        return new BaseResponse<>(
                true,
                "User fetched",
                user
        );
    }
}