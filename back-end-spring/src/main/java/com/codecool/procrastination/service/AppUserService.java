package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.repositories.AppUserRepository;
import com.codecool.procrastination.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {


    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AppUserService(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.jwtUtil = jwtUtil;
    }

    public AppUser getUserById(UUID id) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user found by given id.\n");
        }
    }

    public AppUser getUserByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.getUserByEmail(email);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        } else {
            throw new NoSuchElementException("No user found by given email.\n");
        }
    }

    public boolean IsEmailPresent(String email) {
        return appUserRepository.existsByEmail(email);
    }

    public ResponseEntity<String> registerUser(AppUser appUser) {
        if (appUser.getRealUserName() == null || appUser.getEmail() == null || appUser.getPassword() == null
                || appUser.getGitProfile() == null || appUser.getJourneyProfile() == null) {
            throw new CustomExceptions.MissingAttributeException("Missing one or more attribute(s) in AppUser\n");
        } else {
            if (IsEmailPresent(appUser.getEmail())) {
                throw new CustomExceptions.EmailAlreadyUsedException("Email is already registered.\n");
            } else {
                appUser.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
                appUser.setEnabled(true);
                appUser.setAccountNonExpired(true);
                appUser.setAccountNonLocked(true);
                appUser.setCredentialsNonExpired(true);
                appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
                appUserRepository.saveAndFlush(appUser);
            }
        }
        String token = jwtUtil.createToken(new HashMap<>(), appUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    // TODO delete after auth?
//    public String loginUser(AppUser appUser) {
//        Optional<AppUser> optionalAppUser = appUserRepository.getUserByEmail(appUser.getEmail());
//        if (optionalAppUser.isPresent()) {
//            AppUser savedAppUser = optionalAppUser.get();
//            // TODO hashed passwords
//            if (savedAppUser.getPassword().equals(appUser.getPassword())) {
//                // TODO hashed token
//                return savedAppUser.getId().toString();
//            } else {
//                throw new CustomExceptions.WrongEmailOrPasswordException("Unable to login.\n");
//            }
//        } else {
//            throw new CustomExceptions.WrongEmailOrPasswordException("Unable to login.\n");
//        }
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this::getUserByEmail;
    }
}
