package com.amdevelopment.NetCracker_Project.repositories;

import com.amdevelopment.NetCracker_Project.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query(
            value = "SELECT * FROM users WHERE user_id = :id",
            nativeQuery = true
    )
    User getUserById(@Param("id") Integer id);

    @Query(
            value = "SELECT * FROM users " +
                    "WHERE " +
                    "(:name IS NULL OR users.first_name = :name) " +
                    "AND (:lastName IS NULL OR users.last_name = :lastName) " +
                    "AND (:id IS NULL OR users.user_id = :id) " +
                    "AND (:email IS NULL OR users.email = :email) " +
                    "AND (users.role_id = 2)",
            nativeQuery = true
    )
    Iterable<User> getUsersByData(@Param("name") String name, @Param("lastName") String lastName, @Param("id") Integer id, @Param("email") String email);

    @Query(
            value = "SELECT * FROM users " +
                    "WHERE " +
                    "(:name IS NULL OR users.first_name = :name) " +
                    "AND (:lastName IS NULL OR users.last_name = :lastName) " +
                    "AND (:id IS NULL OR users.user_id = :id) " +
                    "AND (:email IS NULL OR users.email = :email) " +
                    "AND (users.role_id = 1)",
            nativeQuery = true
    )
    Iterable<User> getAdminsByData(@Param("name") String name, @Param("lastName") String lastName, @Param("id") Integer id, @Param("email") String email);

    @Query(
            value = "INSERT INTO users(role_id, first_name, last_name, address, email, phone_number, picture) " +
                    "VALUES(" +
                    ":id, " +
                    ":name, " +
                    ":lastName, " +
                    ":address, " +
                    ":email, " +
                    ":number, " +
                    ":picture" +
                    ")",
            nativeQuery = true
    )
    void insertNewUser(@Param("name") String name, @Param("lastName") String lastName, @Param("id") Integer id, @Param("address") String address, @Param("email") String email, @Param("number") String number, @Param("picture") String picture);

}
