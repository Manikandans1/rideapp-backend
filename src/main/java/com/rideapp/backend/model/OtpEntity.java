package com.rideapp.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "otp")
@Data
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String otp;
}