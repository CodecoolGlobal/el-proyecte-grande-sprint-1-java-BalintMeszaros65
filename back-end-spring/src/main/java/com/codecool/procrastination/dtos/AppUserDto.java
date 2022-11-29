package com.codecool.procrastination.dtos;

import com.codecool.procrastination.model.AppUser;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link AppUser} entity
 */
public class AppUserDto implements Serializable {
    private final UUID id;
    private final URL gitProfile;
    private final URL journeyProfile;
    private final String userName;
    private final String email;

    public AppUserDto(UUID id, URL gitProfile, URL journeyProfile, String userName, String email) {
        this.id = id;
        this.gitProfile = gitProfile;
        this.journeyProfile = journeyProfile;
        this.userName = userName;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserDto entity = (AppUserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.gitProfile, entity.gitProfile) &&
                Objects.equals(this.journeyProfile, entity.journeyProfile) &&
                Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gitProfile, journeyProfile, userName, email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "gitProfile = " + gitProfile + ", " +
                "journeyProfile = " + journeyProfile + ", " +
                "userName = " + userName + ", " +
                "email = " + email + ")";
    }
}