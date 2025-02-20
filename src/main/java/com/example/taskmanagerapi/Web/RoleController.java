package com.example.taskmanagerapi.Web;

import com.example.taskmanagerapi.Models.Role;
import com.example.taskmanagerapi.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.listAllRoles());
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Role> findRoleByName(@PathVariable String name) {
        return ResponseEntity.ok(roleService.findRoleByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findRoleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Role> editRole(@PathVariable Long id, @RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(role, id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}
