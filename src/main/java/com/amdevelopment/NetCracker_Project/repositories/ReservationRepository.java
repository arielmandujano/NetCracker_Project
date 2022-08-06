package com.amdevelopment.NetCracker_Project.repositories;

import com.amdevelopment.NetCracker_Project.models.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    @Query(
            value = "SELECT * FROM reservations",
            nativeQuery = true
    )
    Iterable<Reservation> getAllReservations();

    @Query(
            value = "SELECT * FROM reservations WHERE reservations.user_id = :id",
            nativeQuery = true
    )
    Iterable<Reservation> getReservationsByUserId(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM reservations WHERE reservations.reservation_id = :id",
            nativeQuery = true
    )
    Reservation getReservationById(@Param("id") Integer id);

    @Modifying
    @Query(
            value = "INSERT INTO reservations(car_id, user_id, reservation_date, start_date, end_date, total_amount, format_of_payment, returned) " +
                    "VALUES(" +
                    ":carId, " +
                    ":userId, " +
                    ":reservationDate, " +
                    ":startDate, " +
                    ":endDate, " +
                    ":totalAmount, " +
                    ":payFormat," +
                    ":returned" +
                    ")",
            nativeQuery = true
    )
    void insertNewReservation(@Param("carId") Integer carId, @Param("userId") Integer userId, @Param("reservationDate") Date reservationDate, @Param("startDate") Date start, @Param("endDate") Date end, @Param("totalAmount") BigDecimal totalAmount, @Param("payFormat") String format, @Param("returned") Boolean returned);

    @Query(
            value = "SELECT DATEDIFF( :day2, :day1)",
            nativeQuery = true
    )
    int daysBetweenDates(@Param("day1") Date day1, @Param("day2") Date day2);
}
