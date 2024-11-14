package com.example.aksphw.service;

import com.example.aksphw.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Optional<Vehicle> getVehicleById(long id);

    List<Vehicle> getVehicleByColor(String color);
}
