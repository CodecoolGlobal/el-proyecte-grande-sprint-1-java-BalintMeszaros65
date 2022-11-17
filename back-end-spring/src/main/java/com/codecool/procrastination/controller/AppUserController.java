package com.codecool.procrastination.controller;

import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @CrossOrigin
    @PostMapping("/api/user/registration")
    public void registerUser(@RequestBody AppUser appUser) {
        appUserService.saveUser(appUser);
    }

    @CrossOrigin
    @PostMapping("/api/user/registration/check-email")
    @ResponseBody
    public boolean isEmailFree(@RequestParam String email) {
        return !appUserService.checkIfEmailIsPresent(email);
    }

    @CrossOrigin
    @PostMapping("/api/user/login")
    @ResponseBody
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        AppUser appUser;
        if (!isEmailFree(email)) {
            try {
                appUser = appUserService.getUserByEmail(email);
                // TODO hash password
                if (appUser.getPassword().equals(password)) {
                    // TODO hash
                    return appUser.getId().toString();
                } else {
                    return "";
                }
            } catch (NoSuchElementException exception) {
                return "";
            }
        } else {
            return "";
        }
    }

    // for Postman testing
    @CrossOrigin
    @PostMapping("/api/user/email")
    @ResponseBody
    public AppUser getUserByEmail(@RequestParam String email) {
        return appUserService.getUserByEmail(email);
    }

}
