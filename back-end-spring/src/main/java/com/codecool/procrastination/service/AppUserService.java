package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.service.DAO.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppUserService {


    private AppUserDao appUserDao;

    @Autowired
    public AppUserService(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    public void saveUser(AppUser appUser) {
        appUserDao.saveUser(appUser);
    }

    public AppUser getUserById(UUID id) {
        Optional<AppUser> optionalAppUser = appUserDao.getUserById(id);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user find by given id.\n");
        }
    }

    public boolean checkIfEmailIsPresent(String email) {
        Optional<AppUser> optionalAppUser = appUserDao.getUserByEmail(email);
        return optionalAppUser.isPresent();
    }

}
