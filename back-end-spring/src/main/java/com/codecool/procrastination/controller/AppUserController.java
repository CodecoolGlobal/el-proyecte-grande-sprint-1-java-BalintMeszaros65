package com.codecool.procrastination.controller;

import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/api/user/registration")
    public void registerUser(@RequestBody AppUser appUser) {
        appUserService.saveUser(appUser);
    }

    @PostMapping("/api/user/registration/check-email")
    @ResponseBody
    public boolean isEmailFree(@RequestParam String email) {
        return appUserService.checkIfEmailIsPresent(email);
    }

}
