package com.codecool.procrastination.controller;

import com.codecool.procrastination.dto.AppUserDto;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AppUserController {
    // TODO remove Crossorigin with url
    // TODO return dtos
    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService appUserService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/user")
    public boolean userExists() {
        return appUserService.userExists();
    }

    @GetMapping("/api/user/get-details")
    public AppUserDto getUserDetails() {
        return appUserService.getUserDetails();
    }

    @PostMapping("/api/user/registration")
    public ResponseEntity<String> registerUser(@RequestBody AppUser appUser) {
        return appUserService.registerUser(appUser);
    }

    @GetMapping("/api/user/registration/check-email/{email}")
    @ResponseBody
    public boolean isEmailFree(@PathVariable String email) {
        return !appUserService.IsEmailPresent(email);
    }

    // TODO validate user then give back token to frontend
    @PostMapping("/api/user/login")
    @ResponseBody
    public ResponseEntity<String> loginUser(@RequestBody AppUser appUser) {
        return appUserService.loginUser(appUser);
    }
}
