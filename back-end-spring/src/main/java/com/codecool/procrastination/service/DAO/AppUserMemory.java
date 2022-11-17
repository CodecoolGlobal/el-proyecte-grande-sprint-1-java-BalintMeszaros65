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
    public AppUser getUserById(UUID id) {
        Optional<AppUser> optionalAppUser = users.stream()
                .filter(appUser -> appUser.getId().equals(id))
                .findFirst();
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user find by given id.\n");
        }
    }

    @Override
    public AppUser getUserByEmail(String email) {
        Optional<AppUser> optionalAppUser = users.stream()
                .filter(appUser -> appUser.getEmail().equals(email))
                .findFirst();
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user find by given email.\n");
        }
    }

    @Override
    public void updateUser(AppUser updatedUser) {
        UUID idToBeUpdated = updatedUser.getId();
        AppUser oldUser = getUserById(idToBeUpdated);
        users.remove(oldUser);
        users.add(updatedUser);
    }

    @Override
    public void deleteUserById(UUID id) {
        users.remove(getUserById(id));
    }
}
