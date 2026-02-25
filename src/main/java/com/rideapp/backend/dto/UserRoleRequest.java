package com.rideapp.backend.dto;

import lombok.Data;

@Data
public class UserRoleRequest {

    private String phone;
    private String role;
}