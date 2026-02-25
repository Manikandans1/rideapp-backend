package com.rideapp.backend.service;

import com.rideapp.backend.model.UserEntity;
import com.rideapp.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    private String normalizePhone(String phone) {

        if (phone == null) return null;

        phone = phone.trim();

        // remove spaces
        phone = phone.replaceAll("\\s+", "");

        // ensure +91 format (India)
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
    public UserEntity saveRole(UserEntity user) {

        String normalized = normalizePhone(user.getPhone());
        user.setPhone(normalized);

        UserEntity existing =
                repo.findByPhone(normalized).orElse(null);

        if (existing != null) {
            return existing;
        }

        return repo.save(user);
    }


    @Override
    public UserEntity getUser(String phone) {

        String normalized = normalizePhone(phone);

        return repo.findByPhone(normalized).orElse(null);
    }

}