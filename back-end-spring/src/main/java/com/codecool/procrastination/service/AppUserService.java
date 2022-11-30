package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppUserService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getUserById(UUID id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user find by given id.\n");
        }
    }

    public AppUser getUserByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.getUserByEmail(email);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user find by given email.\n");
        }
    }

    public boolean checkIfEmailIsPresent(String email) {
        return appUserRepository.existsByEmail(email);
    }

    public void registerUser(AppUser appUser) {
        if (appUser.getUserName() == null || appUser.getEmail() == null || appUser.getPassword() == null
                || appUser.getGitProfile() == null || appUser.getJourneyProfile() == null) {
            throw new NullPointerException("Missing one or more attribute(s) in AppUser\n");
        } else {
            appUserRepository.save(appUser);
        }
    }

    public String loginUser(AppUser appUser) {
        Optional<AppUser> optionalAppUser = appUserRepository.getUserByEmail(appUser.getEmail());
        if (optionalAppUser.isPresent()) {
            AppUser savedAppUser = optionalAppUser.get();
            // TODO hashed passwords
            if (savedAppUser.getPassword().equals(appUser.getPassword())) {
                // TODO hashed token
                return savedAppUser.getId().toString();
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
