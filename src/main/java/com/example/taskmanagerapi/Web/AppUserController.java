package com.example.taskmanagerapi.Web;

import com.example.taskmanagerapi.Models.AppUser;
import com.example.taskmanagerapi.Models.DTOs.AssignRoleRequest;
import com.example.taskmanagerapi.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> findAllUsers() {
        return ResponseEntity.ok(this.appUserService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.appUserService.findUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.appUserService.findUserByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        return ResponseEntity.ok(this.appUserService.save(appUser));
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<AppUser> editUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        return ResponseEntity.ok(this.appUserService.update(appUser, id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        this.appUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<AppUser>> findUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.appUserService.findUserRoles(username));
    }

    @PostMapping("/assign-role")
    public ResponseEntity<AppUser> assignRoleToUser(@RequestBody AssignRoleRequest request) {
        return ResponseEntity.ok(this.appUserService.assignRoleToUser(request.getUsername(), request.getRoleId()));
    }
}
