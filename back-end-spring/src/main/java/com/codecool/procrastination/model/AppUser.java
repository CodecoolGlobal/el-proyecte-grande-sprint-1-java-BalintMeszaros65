package com.codecool.procrastination.model;

import java.net.URL;
import java.util.UUID;

public class AppUser {
    private UUID id;
    private URL gitProfile;
    private URL journeyProfile;
    private String userName;
    private String email;
    // TODO authentication and hash
    private String password;

    public AppUser(URL gitProfile, URL journeyProfile, String userName, String email, String password) {
        this.id = UUID.randomUUID();
        this.gitProfile = gitProfile;
        this.journeyProfile = journeyProfile;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public URL getGitProfile() {
        return gitProfile;
    }

    public URL getJourneyProfile() {
        return journeyProfile;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
