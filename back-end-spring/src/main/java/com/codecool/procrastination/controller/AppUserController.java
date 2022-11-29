package com.codecool.procrastination.controller;

import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.model.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService appUserService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }


    // TODO return response error if the appUser misses attributes
    @PostMapping("/api/user/registration")
    public void registerUser(@RequestBody AppUser appUser) {
        appUserService.saveUser(appUser);
    }

    @PostMapping("/api/user/registration/check-email/{email}")
    @ResponseBody
    public boolean isEmailFree(@PathVariable String email) {
        return appUserService.checkIfEmailIsPresent(email);
    }

}
