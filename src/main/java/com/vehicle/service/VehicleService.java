package com.vehicle.service;

import com.vehicle.entity.Vehicle;
import com.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAll().stream()
                .filter(vehicle -> !vehicle.isRented())
                .collect(Collectors.toList());
    }

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(int id) {
        Optional<Vehicle> optional = vehicleRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteVehicleById(int id) {
        this.vehicleRepository.deleteById(id);
    }
}