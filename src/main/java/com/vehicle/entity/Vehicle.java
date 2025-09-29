package com.vehicle.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;

    @Column(name = "rent_per_day")
    private double rentPerDay;

    private boolean isRented = false; // New field

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // New field

    public Vehicle() {}

    public Vehicle(String name, String type, double rentPerDay) {
        this.name = name;
        this.type = type;
        this.rentPerDay = rentPerDay;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getRentPerDay() { return rentPerDay; }
    public void setRentPerDay(double rentPerDay) { this.rentPerDay = rentPerDay; }

    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}