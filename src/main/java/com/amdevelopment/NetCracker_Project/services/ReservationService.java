package com.amdevelopment.NetCracker_Project.services;

import com.amdevelopment.NetCracker_Project.config.exceptions.BadRequestException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DataBaseException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DateFormatException;
import com.amdevelopment.NetCracker_Project.config.exceptions.NotFoundException;
import com.amdevelopment.NetCracker_Project.models.Car;
import com.amdevelopment.NetCracker_Project.models.Reservation;
import com.amdevelopment.NetCracker_Project.models.User;
import com.amdevelopment.NetCracker_Project.repositories.CarRepository;
import com.amdevelopment.NetCracker_Project.repositories.ReservationRepository;
import com.amdevelopment.NetCracker_Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    public Reservation getReservationById(Integer id) {
        if(id <= 0) {
            throw new BadRequestException("Reservation id cannot be 0 or null.");
        }
        Reservation reservation = reservationRepository.getReservationById(id);
        if(reservation == null) {
            throw new NotFoundException("There is no reservation with id = " + id);
        }
        return reservation;
    }

    public Iterable<Reservation> getReservationsByUserId(Integer id) {
        if(id <= 0) {
            throw new BadRequestException("User id cannot be 0 or null.");
        }
        User user = userRepository.getUserById(id);
        if(user == null) {
            throw new NotFoundException("There is no user with id = " + id);
        }
        return reservationRepository.getReservationsByUserId(id);
    }

    public void insertNewReservation(Integer carId, Integer userId, String reservationDate, String start, String end, String format, Boolean returned) {
        if(carId <= 0) {
            throw new BadRequestException("Car id cannot be 0 or negative.");
        }
        Car car = carRepository.getCarById(carId);
        if (car == null) {
            throw new NotFoundException("Car id must be the id of an existing car.");
        }
        if(userId <= 0) {
            throw new BadRequestException("User id cannot be 0 or negative.");
        }
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User id must be the id of an existing user.");
        }
        Date resDate;
        Date startDate;
        Date endDate;
        int difDays;
        BigDecimal totalAmount;
        try {
            resDate = Date.valueOf(reservationDate);
            startDate = Date.valueOf(start);
            endDate = Date.valueOf(end);
            if(endDate.before(startDate)) {
                throw new BadRequestException("Reservation end date cannot be before reservation end date.");
            }
            difDays = reservationRepository.daysBetweenDates(startDate,endDate);
            totalAmount = car.getPricePerDay().multiply(new BigDecimal(difDays));
        } catch (IllegalArgumentException e) {
            throw new DateFormatException("Invalid date string format. ");
        }
        if(format.equals("") || format == null) {
            throw new BadRequestException("Payment format cannot be empty or be null.");
        }
        if(returned == null) {
            throw new BadRequestException("Returned cannot be empty or be null.");
        }
        try {
            reservationRepository.insertNewReservation(carId,userId, resDate, startDate, endDate, totalAmount, format, returned);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while writing the reservation information");
        }
    }

}
