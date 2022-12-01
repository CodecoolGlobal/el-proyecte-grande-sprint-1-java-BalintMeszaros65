package com.codecool.procrastination.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ProjectMessage {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;
    @NotNull
    @ManyToOne
    Project project;
    @NotNull
    @ManyToOne
    AppUser appUser;
    @NotNull
    String message;
    @CreationTimestamp
    Timestamp timestamp;

    public ProjectMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMessage that = (ProjectMessage) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    // TODO slice nanosec and sec
    public String toString() {
        return timestamp.toString()  +
                " - " +
                appUser.getUserName() +
                ": " +
                message;
    }
}
