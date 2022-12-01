package com.codecool.procrastination.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project {
    // TODO gitRepo unique

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotNull
    private String projectName;
    @NotNull
    private String teamName;
    @NotNull
    private String gitRepo;
    private boolean finished;
    @ManyToMany
    private Set<AppUser> members;

    public Project() {
        this.finished = false;
        this.members = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getGitRepo() {
        return gitRepo;
    }

    public boolean isFinished() {
        return finished;
    }

    public Set<AppUser> getMembers() {
        return members;
    }

    public void addNewUser(AppUser user) {
        members.add(user);
    }

    public void changeStatus () {
        finished = !finished;
    }

    public boolean findUser(UUID userId) {
        for (AppUser user: members) {
            if (userId == user.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", gitRepo='" + gitRepo + '\'' +
                ", finished=" + finished +
                ", members=" + members +
                '}';
    }
}
