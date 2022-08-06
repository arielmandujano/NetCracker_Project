package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Car;
import com.amdevelopment.NetCracker_Project.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getAllCars")
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/getCarById")
    public Car getCarById(@RequestParam Integer id) {
        return carService.getCarById(id);
    }

    @PostMapping("/saveNewCar")
    public void insertNewCar(@RequestParam Integer id, @RequestParam String registration, @RequestParam BigDecimal pricePerDay) {
        carService.insertNewCar(id, registration, pricePerDay);
    }

    @PutMapping("/updateCar")
    public void updateCar(@RequestParam Integer id, @RequestParam Integer modelId, @RequestParam String registration, @RequestParam BigDecimal pricePerDay) {
        carService.updateCar(id, modelId, registration, pricePerDay);
    }

    @GetMapping("/getCarsFiltered")
    public Iterable<Car> getCarsFiltered(@RequestParam(required = false) String brand, @RequestParam(required = false) Integer year, @RequestParam(required = false, defaultValue = "c.car_id") String orderBy, @RequestParam(required = false, defaultValue = "ASC") String order) {
        return carService.getDetailedCarsFiltered(brand,year,orderBy,order);
    }

    @GetMapping("/getAvailableCars")
    public Iterable<Car> getAvailableCarsDir(@RequestParam String startDate, @RequestParam String endDate) {
        return carService.getAvialableCars(startDate, endDate);
    }

}
