package com.vehicle.controller;

import com.vehicle.entity.User;
import com.vehicle.entity.Vehicle;
import com.vehicle.service.UserService;
import com.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // REMOVE THE ENTIRE loginUser METHOD - Spring Security handles this
    // @PostMapping("/login")
    // public String loginUser(@RequestParam("id") int id) { ... }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user); // We will update this service next
        return "redirect:/login"; // Redirect to login page after registration
    }

    @GetMapping("/user-dashboard/{userId}")
    public String userDashboard(@PathVariable("userId") int userId, Model model) {
        User user = userService.getUserById(userId);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("availableVehicles", vehicleService.getAvailableVehicles());
            model.addAttribute("rentedVehicles", user.getRentedVehicles());
            return "user-dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/rent-vehicle/{userId}/{vehicleId}")
    public String rentVehicle(@PathVariable("userId") int userId, @PathVariable("vehicleId") int vehicleId) {
        User user = userService.getUserById(userId);
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        if (user != null && vehicle != null && !vehicle.isRented()) {
            vehicle.setRented(true);
            vehicle.setUser(user);
            vehicleService.saveVehicle(vehicle);
        }
        return "redirect:/user-dashboard/" + userId;
    }

    @GetMapping("/return-vehicle/{userId}/{vehicleId}")
    public String returnVehicle(@PathVariable("userId") int userId, @PathVariable("vehicleId") int vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        if (vehicle != null && vehicle.isRented() && vehicle.getUser().getId() == userId) {
            vehicle.setRented(false);
            vehicle.setUser(null);
            vehicleService.saveVehicle(vehicle);
        }
        return "redirect:/user-dashboard/" + userId;
    }
}