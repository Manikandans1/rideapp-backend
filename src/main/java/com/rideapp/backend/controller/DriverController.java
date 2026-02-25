package com.rideapp.backend.controller;

import com.rideapp.backend.dto.BaseResponse;
import com.rideapp.backend.dto.DriverRequest;
import com.rideapp.backend.model.DriverEntity;
import com.rideapp.backend.repository.DriverRepository;
import com.rideapp.backend.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    private final DriverRepository driverRepository;

    @PostMapping("/save")
    public BaseResponse<DriverEntity> saveDriver(
            @RequestBody DriverRequest request){

        DriverEntity driver = new DriverEntity();

        driver.setName(request.getName());
        driver.setCity(request.getCity());
        driver.setPhone(request.getPhone());
        driver.setPhotoUrl(request.getPhotoUrl());
        driver.setVehicleType(request.getVehicleType());

        // Auto default values for UI
        driver.setRating(4.5);
        driver.setTrips(0);
        driver.setDistanceKm(Math.random() * 5);
        driver.setIsOnline(true);

        return new BaseResponse<>(
                true,
                "Driver saved",
                driverService.saveDriver(driver)
        );
    }

    @GetMapping("/by-city")
    public BaseResponse<List<DriverEntity>> getDrivers(@RequestParam String city){

        return new BaseResponse<>(
                true,
                "Drivers fetched",
                driverService.getDriversByCity(city)
        );
    }

    @GetMapping("/exists")
    public BaseResponse<Boolean> checkDriverExists(
            @RequestParam String phone){

        boolean exists = driverRepository.existsByPhone(phone);

        return new BaseResponse<>(
                true,
                "Checked",
                exists
        );
    }


    @GetMapping("/by-phone")
    public BaseResponse<DriverEntity> getDriverByPhone(
            @RequestParam String phone) {

        DriverEntity driver =
                driverService.getDriverByPhone(phone);

        if (driver == null) {
            return new BaseResponse<>(
                    false,
                    "Driver not found",
                    null
            );
        }

        return new BaseResponse<>(
                true,
                "Driver found",
                driver
        );
    }


}