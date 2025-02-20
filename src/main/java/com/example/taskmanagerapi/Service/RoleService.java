package com.example.taskmanagerapi.Service;

import com.example.taskmanagerapi.Models.Exceptions.InvalidRoleIdException;
import com.example.taskmanagerapi.Models.Role;
import com.example.taskmanagerapi.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(InvalidRoleIdException::new);
    }

    public Role createRole(Role role) {
        Role newRole = new Role();
        newRole.setName(role.getName());
        roleRepository.save(newRole);
        return newRole;
    }

    public Role updateRole(Role role, Long id) {
        return this.roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setName(role.getName());
                    this.roleRepository.save(existingRole);
                    return existingRole;
                }).orElseThrow(InvalidRoleIdException::new);
    }

    public void deleteRoleById(Long id) {
        if (this.roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new InvalidRoleIdException();
        }
    }
}
