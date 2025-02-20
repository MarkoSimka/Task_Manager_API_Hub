package com.example.taskmanagerapi.Repository;

import com.example.taskmanagerapi.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT role FROM Role role WHERE role.name LIKE CONCAT(:name, '%')")
    Role findByName(String name);
}