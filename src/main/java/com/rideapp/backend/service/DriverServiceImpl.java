package com.rideapp.backend.service;

import com.rideapp.backend.model.DriverEntity;
import com.rideapp.backend.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repo;

    @Override
    public DriverEntity saveDriver(DriverEntity driver) {

        driver.setPhone(normalizePhone(driver.getPhone()));

        return repo.save(driver);
    }

    @Override
    public List<DriverEntity> getDriversByCity(String city) {
        return repo.findByCityIgnoreCase(city);
    }

    @Override
    public DriverEntity getDriverByPhone(String phone) {

        String normalized = normalizePhone(phone);

        return repo.findByPhone(normalized).orElse(null);
    }

    private String normalizePhone(String phone) {

        if (phone == null) return null;

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
}