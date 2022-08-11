package com.amdevelopment.NetCracker_Project.repositories;

import com.amdevelopment.NetCracker_Project.models.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query(
            value = "SELECT * FROM cars",
            nativeQuery = true
    )
    Iterable<Car> getAllCars();

    @Query(
            value = "SELECT * " +
                    "FROM cars " +
                    "WHERE car_id = :id",
            nativeQuery = true
    )
    Car getCarById(@Param("id") Integer id);

    @Modifying
    @Query(
            value = "INSERT INTO cars(model_id, car_registration, price_per_day) " +
                    "VALUES (:model_id, :registration, :price)",
            nativeQuery = true
    )
    void insertNewCar(@Param("model_id") Integer modelId, @Param("registration") String carRegistration, @Param("price") BigDecimal pricePerDay);

    @Modifying
    @Query(
            value = "UPDATE cars " +
                    "SET " +
                    "model_id = :model_id, " +
                    "car_registration = :registration, " +
                    "price_per_day = :price " +
                    "WHERE car_id = :car_id",
            nativeQuery = true
    )
    void updateCar(@Param("car_id") Integer carId, @Param("model_id") Integer modelId, @Param("registration") String carRegistration, @Param("price") BigDecimal pricePerDay);

    @Query(
            value = "SELECT * " +
                    "FROM cars AS c INNER JOIN models AS m ON c.model_id = m.model_id " +
                    "WHERE (:brand IS NULL OR m.brand = :brand) AND (:year IS NULL OR m.model_year = :year) " +
                    "ORDER BY :orderBy :order",
            nativeQuery = true
    )
    Iterable<Car> getDetailedCarsFiltered(@Param("brand") String brand, @Param("year") Integer year, @Param("orderBy") String orderBy, @Param("order") String order);

    @Query(
            value = "SELECT * FROM cars AS c " +
                    "INNER JOIN reservations AS r ON c.car_id = r.car_id " +
                    "WHERE " +
                    "((r.start_date <= :startDate AND r.end_date >= :startDate) " +
                    "OR (r.end_date >= :endDate AND r.start_date <= :endDate) " +
                    "OR (r.start_date >= :startDate AND r.end_date <= :endDate)) " +
                    "AND r.returned = 1",
            nativeQuery = true
    )
    Iterable<Car> getOccupiedCars(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(
            value = "SELECT * FROM cars AS c " +
                    "INNER JOIN models as m ON c.model_id = m.model_id " +
                    "WHERE " +
                    "(:model IS NULL OR m.model_name = :model) " +
                    "AND (:brand IS NULL OR m.brand = :brand) " +
                    "AND (:year IS NULL OR m.model_year = :year)" +
                    "AND c.car_id NOT IN" +
                    "(SELECT DISTINCT c.car_id FROM cars AS c " +
                    "INNER JOIN reservations AS r ON c.car_id = r.car_id " +
                    "WHERE " +
                    "(r.start_date BETWEEN :startDate AND :endDate) " +
                    "OR ( r.end_date BETWEEN :startDate AND :endDate) " +
                    "AND r.returned = 0) " ,
            nativeQuery = true
    )
    Iterable<Car> getAvailableCars(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("model") String model, @Param("brand") String brand, @Param("year") Integer year);
}
