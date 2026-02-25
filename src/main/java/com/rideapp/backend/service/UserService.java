package com.rideapp.backend.service;

import com.rideapp.backend.model.UserEntity;

public interface UserService {

    UserEntity saveRole(UserEntity user);

    UserEntity getUser(String phone);
}