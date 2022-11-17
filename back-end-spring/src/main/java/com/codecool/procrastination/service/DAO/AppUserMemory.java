package com.codecool.procrastination.service.DAO;


import com.codecool.procrastination.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class AppUserMemory implements AppUserDao {

    Set<AppUser> users = new HashSet<>();

    @Override
    public void saveUser(AppUser appUser) {
        users.add(appUser);
    }

    @Override
    public Optional<AppUser> getUserById(UUID id) {
        return users.stream()
                .filter(appUser -> appUser.getId().equals(id))
                .findFirst();

    }

    @Override
    public Optional<AppUser> getUserByEmail(String email) {
        return users.stream()
                .filter(appUser -> appUser.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void updateUser(AppUser oldUser, AppUser updatedUser) {
        users.remove(oldUser);
        users.add(updatedUser);
    }

    @Override
    public void deleteUserById(AppUser appUser) {
        users.remove(appUser);
    }
}
