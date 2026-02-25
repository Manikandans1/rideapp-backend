package com.rideapp.backend.repository;

import com.rideapp.backend.model.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    List<DriverEntity> findByCityIgnoreCase(String city);
    boolean existsByPhone(String phone);
    Optional<DriverEntity> findByPhone(String phone);
}