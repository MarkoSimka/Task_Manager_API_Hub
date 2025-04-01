package com.example.taskmanagerapi.Repository;

import com.example.taskmanagerapi.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByEmail(String email);

    Optional<AppUser> findUserByUsername(String username);

    @Query("SELECT u.roles FROM AppUser u WHERE u.username = :username")
    List<AppUser> findUserRoles(@Param("username") String username);
}
