package com.codecool.procrastination.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotNull
    private String gitProfile;
    @NotNull
    private String journeyProfile;
    @NotNull
    private String userName;
    // TODO authentication and hash
    @NotNull
    private String email;
    @NotNull
    private String password;

    public AppUser() {

    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", gitProfile=" + gitProfile +
                ", journeyProfile=" + journeyProfile +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
