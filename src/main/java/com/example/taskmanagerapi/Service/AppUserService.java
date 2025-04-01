package com.example.taskmanagerapi.Service;

import com.example.taskmanagerapi.Models.AppUser;
import com.example.taskmanagerapi.Models.Exceptions.InvalidUserEmailException;
import com.example.taskmanagerapi.Models.Exceptions.InvalidUserIdException;
import com.example.taskmanagerapi.Models.Exceptions.InvalidUsernameException;
import com.example.taskmanagerapi.Models.Role;
import com.example.taskmanagerapi.Repository.AppUserRepository;
import com.example.taskmanagerapi.Repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    public List<AppUser> findAllUsers() {
        return this.appUserRepository.findAll();
    }

    public AppUser findUserById(Long id) {
        return this.appUserRepository.findById(id).orElseThrow(InvalidUserIdException::new);
    }

    public AppUser findUserByEmail(String email) {
        return this.appUserRepository.findUserByEmail(email).orElseThrow(InvalidUserEmailException::new);
    }

    public AppUser save(AppUser appUser) {
        AppUser appUserSaved = new AppUser();
        appUserSaved.setUsername(appUser.getUsername());
        appUserSaved.setPassword(appUser.getPassword());
        appUserSaved.setRoles(appUser.getRoles());
        appUserSaved.setEmail(appUser.getEmail());
        this.appUserRepository.save(appUserSaved);
        return appUser;
    }

    public AppUser update(AppUser appUser, Long id) {
        return this.appUserRepository.findById(id)
                .map(existingAppUser -> {
                    existingAppUser.setUsername(appUser.getUsername());
                    existingAppUser.setPassword(appUser.getPassword());
                    existingAppUser.setRoles(appUser.getRoles());
                    existingAppUser.setEmail(appUser.getEmail());
                    this.appUserRepository.save(existingAppUser);
                    return existingAppUser;
                }).orElseThrow(InvalidUserIdException::new);
    }

    public void deleteById(Long id) {
        if (this.appUserRepository.findById(id).isPresent()) {
            this.appUserRepository.deleteById(id);
        } else {
            throw new InvalidUserIdException();
        }
    }

    public List<AppUser> findUserRoles(String username) {
        return this.appUserRepository.findUserRoles(username);
    }

    @Transactional
    public AppUser assignRoleToUser(String username, Long roleId) {
        AppUser modifiedUsersRole = this.appUserRepository.findUserByUsername(username).orElseThrow(InvalidUsernameException::new);
        Role foundRole = this.roleRepository.findById(roleId).orElseThrow(InvalidUserIdException::new);
        if (!modifiedUsersRole.getRoles().contains(foundRole)) {
            modifiedUsersRole.getRoles().add(foundRole);
            this.appUserRepository.save(modifiedUsersRole);
        }
        return modifiedUsersRole;
    }
}
