package com.codecool.procrastination.service.DAO;

import com.codecool.procrastination.model.AppUser;

import java.util.Optional;
import java.util.UUID;

public interface AppUserDao {

    void saveUser(AppUser appUser);

    Optional<AppUser> getUserById(UUID id);

    Optional<AppUser> getUserByEmail(String email);

    void updateUser(AppUser oldUser, AppUser updatedUser);

    void deleteUserById(AppUser appUser);
}
