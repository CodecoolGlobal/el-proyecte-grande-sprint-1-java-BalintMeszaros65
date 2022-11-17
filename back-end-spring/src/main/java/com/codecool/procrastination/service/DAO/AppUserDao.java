package com.codecool.procrastination.service.DAO;

import com.codecool.procrastination.model.AppUser;

import java.util.UUID;

public interface AppUserDao {

    void saveUser(AppUser appUser);

    AppUser getUserById(UUID id);

    AppUser getUserByEmail(String email);

    void updateUser(AppUser appUser);

    void deleteUserById(UUID id);
}
