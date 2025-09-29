package com.vehicle.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password; // <-- ADD THIS LINE

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> rentedVehicles;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; } // <-- ADD THIS
    public void setPassword(String password) { this.password = password; } // <-- AND THIS

    public List<Vehicle> getRentedVehicles() { return rentedVehicles; }
    public void setRentedVehicles(List<Vehicle> rentedVehicles) { this.rentedVehicles = rentedVehicles; }
}