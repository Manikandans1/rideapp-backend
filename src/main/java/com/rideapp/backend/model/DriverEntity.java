package com.rideapp.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drivers")
@Data
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String phone;

    private String vehicleType;
    private Double rating;
    private Integer trips;
    private Double distanceKm;
    private Boolean isOnline;

    private String photoUrl;
}