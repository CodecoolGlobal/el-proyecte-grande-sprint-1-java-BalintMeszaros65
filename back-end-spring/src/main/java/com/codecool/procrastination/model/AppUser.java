package com.codecool.procrastination.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;
import java.util.UUID;

@Entity
public class AppUser {
    // TODO not null annotations

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private URL gitProfile;
    private URL journeyProfile;
    private String userName;
    // TODO authentication and hash
    private String email;
    private String password;

    public AppUser() {

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

    public String getPassword() {
        return password;
    }
}
