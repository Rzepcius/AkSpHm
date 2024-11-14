package com.example.aksphw.service;

import com.example.aksphw.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    List<Vehicle> vehicleList = new ArrayList<>();

    public VehicleServiceImpl() {
        this.vehicleList.add(new Vehicle(1,"Audi","A4", "Silver"));
        this.vehicleList.add(new Vehicle(2,"Opel","Vectra", "BLACK"));
        this.vehicleList.add(new Vehicle(3,"Opel","Astra", "BLUE"));
        this.vehicleList.add(new Vehicle(4,"Audi","Q5", "WHITE"));
        this.vehicleList.add(new Vehicle(5,"BMW","E36", "RED"));
        this.vehicleList.add(new Vehicle(6,"Porsche","911", "RED"));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleList;
    }

    @Override
    public Optional<Vehicle> getVehicleById(long id) {
        return getAllVehicles().stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst();
    }
    @Override
    public List<Vehicle> getVehicleByColor(String color) {
        return getAllVehicles()
                .stream()
                .filter(vehicle -> color.equalsIgnoreCase(vehicle.getColor()))
                .collect(Collectors.toList());
    }

}
