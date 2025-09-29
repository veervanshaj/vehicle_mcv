package com.vehicle.controller;

import com.vehicle.entity.Vehicle;
import com.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // display list of vehicles
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listVehicles", vehicleService.getAllVehicles());
        return "index";
    }

    @GetMapping("/showNewVehicleForm")
    public String showNewVehicleForm(Model model) {
        // create model attribute to bind form data
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "add-vehicle";
    }

    @PostMapping("/saveVehicle")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        // save vehicle to database
        vehicleService.saveVehicle(vehicle);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {

        // get vehicle from the service
        Vehicle vehicle = vehicleService.getVehicleById(id);

        // set vehicle as a model attribute to pre-populate the form
        model.addAttribute("vehicle", vehicle);
        return "edit-vehicle";
    }

    @GetMapping("/deleteVehicle/{id}")
    public String deleteVehicle(@PathVariable(value = "id") int id) {

        // call delete vehicle method
        this.vehicleService.deleteVehicleById(id);
        return "redirect:/";
    }
}