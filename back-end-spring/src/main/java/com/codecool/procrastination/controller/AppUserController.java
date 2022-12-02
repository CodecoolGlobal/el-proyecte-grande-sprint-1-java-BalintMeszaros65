package com.codecool.procrastination.controller;

import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.model.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {
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
        appUserService.registerUser(appUser);
    }

    @CrossOrigin
    @GetMapping("/api/user/registration/check-email/{email}")
    @ResponseBody
    public boolean isEmailFree(@PathVariable String email) {
        return !appUserService.IsEmailPresent(email);
    }

    @CrossOrigin
    @PostMapping("/api/user/login")
    @ResponseBody
    public String loginUser(@RequestBody AppUser appUser) {
        return appUserService.loginUser(appUser);
    }
}
