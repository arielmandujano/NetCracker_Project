package com.amdevelopment.NetCracker_Project.repositories;

import com.amdevelopment.NetCracker_Project.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query(
            value = "SELECT * FROM roles",
            nativeQuery = true
    )
    Iterable<Role> getAllRoles();

    @Query(
            value = "SELECT * " +
                    "FROM roles " +
                    "WHERE role_id = :id",
            nativeQuery = true
    )
    Role getRoleById(@Param("id") Integer id);

    @Modifying
    @Query(
            value = "INSERT INTO roles(name) " +
                    "VALUES (:name)",
            nativeQuery = true
    )
    void insertNewRole(@Param("name") String name);

    @Modifying
    @Query(
            value = "UPDATE roles " +
                    "SET name = :name " +
                    "WHERE role_id = :id",
            nativeQuery = true
    )
    void updateRole(@Param("id") Integer id, @Param("name") String name);


}
