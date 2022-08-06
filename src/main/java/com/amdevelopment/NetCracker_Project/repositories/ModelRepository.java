package com.amdevelopment.NetCracker_Project.repositories;

import com.amdevelopment.NetCracker_Project.models.Model;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {

    @Query(
            value = "SELECT * FROM models",
            nativeQuery = true
    )
    Iterable<Model> getAllModels();

    @Query(
            value = "SELECT * FROM models WHERE model_id = :id",
            nativeQuery = true
    )
    Model getModelById(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM models " +
                    "WHERE " +
                    "(:brand IS NULL OR models.brand = :brand) " +
                    "AND (:year IS NULL OR models.model_year = :year) " +
                    "AND (:type IS NULL OR models.car_type = :type)",
            nativeQuery = true
    )
    Iterable<Model> getModelsFiltered(@Param("brand") String brand, @Param("year") Integer year, @Param("type") String type);

    @Modifying
    @Query(
            value = "INSERT INTO models(model_name, model_year, brand, color, car_type, picture) " +
                    "VALUES (:name, :year, :brand, :color, :type, :picture)",
            nativeQuery = true
    )
    void insertNewModel(@Param("name") String name, @Param("year") Integer year, @Param("brand") String brand, @Param("color") String color, @Param("type") String type, @Param("picture") String picture);

    @Modifying
    @Query(
            value = "UPDATE models " +
                    "SET " +
                    "model_name = :name, " +
                    "model_year = :year, " +
                    "brand = :brand, " +
                    "color = :color, " +
                    "car_type = :type, " +
                    "picture = :picture " +
                    "WHERE model_id = :id",
            nativeQuery = true
    )
    void updateModel(@Param("id") Integer id, @Param("name") String name, @Param("year") Integer year, @Param("brand") String brand, @Param("color") String color, @Param("type") String type, @Param("picture") String picture);

    @Query(
            value = "SELECT DISTINCT brand FROM models ORDER BY brand",
            nativeQuery = true
    )
    Iterable<String> getBrands();

    @Query(
            value = "SELECT DISTINCT color FROM models ORDER BY color",
            nativeQuery = true
    )
    Iterable<String> getColors();

    @Query(
            value = "SELECT DISTINCT car_type FROM models ORDER BY car_type",
            nativeQuery = true
    )
    Iterable<String> getTypes();

    @Query(
            value = "SELECT DISTINCT model_year FROM models ORDER BY model_year",
            nativeQuery = true
    )
    Iterable<Integer> getYears();
}
