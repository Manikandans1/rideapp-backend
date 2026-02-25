package com.rideapp.backend.dto;

import lombok.Data;

@Data
public class DriverRequest {

    private String name;
    private String city;
    private String phone;
    private String vehicleType;
    private String photoUrl;

}