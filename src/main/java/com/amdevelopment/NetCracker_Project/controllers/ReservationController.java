package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Reservation;
import com.amdevelopment.NetCracker_Project.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/getAllReservations")
    public Iterable<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/getReservesByUserId")
    public Iterable<Reservation> getReservationsByUserId(@RequestParam Integer id) {
        return reservationService.getReservationsByUserId(id);
    }

    @GetMapping("/getReservationById")
    public Reservation getReservationById(@RequestParam Integer id){
        return reservationService.getReservationById(id);
    }

    @PostMapping("/insertNewReserve")
    public void insertNewReservation(@RequestParam Integer carId, @RequestParam Integer userId, @RequestParam String reservationDate, @RequestParam String start, @RequestParam String end, @RequestParam String format, @RequestParam Boolean returned) {
        reservationService.insertNewReservation(carId, userId, reservationDate, start, end, format, returned);
    }

}
