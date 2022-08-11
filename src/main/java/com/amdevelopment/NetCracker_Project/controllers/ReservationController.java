package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Reservation;
import com.amdevelopment.NetCracker_Project.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/getAllReservations")
    @PreAuthorize("hasAuthority('Admin')")
    public Iterable<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/getReservesByUserId")
    @PreAuthorize("hasAuthority('Admin')")
    public Iterable<Reservation> getReservationsByUserId(@RequestParam Integer id) {
        return reservationService.getReservationsByUserId(id);
    }

    @GetMapping("/getReservationByIdAndUsername")
    public Reservation getReservationByIdAndUsername(@RequestParam Integer id, @RequestParam String username){
        return reservationService.getReservationByIdAndUsername(id, username);
    }

    @PostMapping("/insertNewReserve")
    public ResponseEntity<?> insertNewReservation(@RequestParam Integer carId, @RequestParam Integer userId, @RequestParam String reservationDate, @RequestParam String start, @RequestParam String end, @RequestParam String formatOfPayment, @RequestParam Boolean returned) {
        reservationService.insertNewReservation(carId, userId, reservationDate, start, end, formatOfPayment, returned);
        Reservation r = reservationService.getLastReservationByUserId(userId).get();
        return new ResponseEntity(r.getReservationId(), HttpStatus.OK);
    }

}
