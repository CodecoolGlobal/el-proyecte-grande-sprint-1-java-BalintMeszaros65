package com.codecool.procrastination.dto;

import com.codecool.procrastination.model.AppUser;

public class AppUserDto {
    private final String gitProfile;
    private final String journeyProfile;
    private final String userName;
    private final String email;

    public AppUserDto(AppUser appUser) {
        this.gitProfile = appUser.getGitProfile();
        this.journeyProfile = appUser.getJourneyProfile();
        this.userName = appUser.getRealUserName();
        this.email = appUser.getEmail();
    }

    public String getGitProfile() {
        return gitProfile;
    }

    public String getJourneyProfile() {
        return journeyProfile;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AppUserDto{" +
                "gitProfile='" + gitProfile + '\'' +
                ", journeyProfile='" + journeyProfile + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
