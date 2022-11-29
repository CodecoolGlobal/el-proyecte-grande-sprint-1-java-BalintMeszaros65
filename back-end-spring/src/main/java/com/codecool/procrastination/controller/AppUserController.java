package com.codecool.procrastination.controller;

import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.model.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class AppUserController {
    // TODO return String response error if the appUser misses attributes
    // TODO Crossorigin with url
    // TODO return dtos

    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService appUserService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }



    @CrossOrigin
    @PostMapping("/api/user/registration")
    public void registerUser(@RequestBody AppUser appUser) {
        appUserService.saveUser(appUser);
    }

    @CrossOrigin
    @PostMapping("/api/user/registration/check-email/{email}")
    @ResponseBody
    public boolean isEmailFree(@PathVariable String email) {
        return appUserService.checkIfEmailIsPresent(email);
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
                    // TODO hashed token
                    return appUser.getId().toString();
                } else {
                    // TODO error
                    return "";
                }
            } catch (NoSuchElementException exception) {
                // TODO error
                return "";
            }
        } else {
            // TODO error
            return "";
        }
    }
}
