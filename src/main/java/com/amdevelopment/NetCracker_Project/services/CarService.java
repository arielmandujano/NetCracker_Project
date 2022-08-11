package com.amdevelopment.NetCracker_Project.services;

import com.amdevelopment.NetCracker_Project.config.exceptions.BadRequestException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DataBaseException;
import com.amdevelopment.NetCracker_Project.config.exceptions.NotFoundException;
import com.amdevelopment.NetCracker_Project.models.Car;
import com.amdevelopment.NetCracker_Project.models.Model;
import com.amdevelopment.NetCracker_Project.repositories.CarRepository;
import com.amdevelopment.NetCracker_Project.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;

@Service
@Transactional
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelRepository modelRepository;

    public CarService(CarRepository carRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
    }

    public Iterable<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCarById(Integer id) {
        if(id <= 0 ) {
            throw new BadRequestException("Car id cannot be 0 or negative.");
        }
        Car car = carRepository.getCarById(id);
        if(car == null) {
            throw new NotFoundException("There is no car with id = " + id);
        }
        return car;
    }

    public void insertNewCar(Integer id, String registration, BigDecimal pricePerDay) {
        if(id <= 0) {
            throw new BadRequestException("Model id cannot be 0 or negative.");
        }
        if(registration.equals("") || registration == null) {
            throw new BadRequestException("The registration number cannot be null or be empty.");
        }
        if(pricePerDay.compareTo(BigDecimal.ZERO) <= 0 || pricePerDay == null) {
            throw new BadRequestException("The price per day must be greater than 0.00 or cannot be null.");
        }
        Model model = modelRepository.getModelById(id);
        if(model == null) {
            throw new NotFoundException("The model id must be the id of an existing model.");
        }
        try {
            carRepository.insertNewCar(id, registration, pricePerDay);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while writing car information.");
        }
    }

    public void updateCar(Integer id, Integer modelId, String registration, BigDecimal pricePerDay) {
        if(id <= 0) {
            throw new BadRequestException("Car id cannot be 0 or negative.");
        }
        if(modelId <= 0) {
            throw new BadRequestException("Model id cannot be 0 or negative.");
        }
        if(registration.equals("") || registration == null) {
            throw new BadRequestException("The registration number cannot be null or be empty.");
        }
        if(pricePerDay.compareTo(BigDecimal.ZERO) <= 0 || pricePerDay == null) {
            throw new BadRequestException("The price per day must be greater than 0.00 or cannot be null.");
        }
        Model model = modelRepository.getModelById(id);
        if(model == null) {
            throw new NotFoundException("The model id must be the id of an existing model.");
        }
        Car car = carRepository.getCarById(id);
        if(car == null) {
            throw new NotFoundException("The car id must be the id of an existing car.");
        }
        try {
            carRepository.updateCar(id, modelId, registration, pricePerDay);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while updating car information.");
        }

    }

    public Iterable<Car> getDetailedCarsFiltered(String brand, Integer year, String orderBy, String order) {
        return carRepository.getDetailedCarsFiltered(brand,year,orderBy,order);
    }

    public Iterable<Car> getOccupiedCars(String startDate, String endDate) {
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        return carRepository.getOccupiedCars(start, end);
    }

    public Iterable<Car> getAvailableCars(String startDate, String endDate, String model, String brand, Integer year) {
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        return carRepository.getAvailableCars(start,end, model, brand, year);
    }

}
