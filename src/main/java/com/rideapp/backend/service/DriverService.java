package com.rideapp.backend.service;

import com.rideapp.backend.model.DriverEntity;

import java.util.List;

public interface DriverService {

    DriverEntity saveDriver(DriverEntity driver);

    List<DriverEntity> getDriversByCity(String city);

    DriverEntity getDriverByPhone(String phone);
}