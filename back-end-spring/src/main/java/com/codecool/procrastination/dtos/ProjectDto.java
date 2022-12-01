package com.codecool.procrastination.dtos;

import com.codecool.procrastination.model.AppUser;

import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ProjectDto implements Serializable {
    private final UUID id;
    private final String projectName;
    private final String teamName;
    private final String gitRepo;
    private boolean finished;
    @ManyToMany
    private Set<AppUser> members;

    public ProjectDto(UUID id, String projectName, String teamName, String gitRepo) {
        this.id = id;
        this.projectName = projectName;
        this.teamName = teamName;
        this.gitRepo = gitRepo;
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

    public void addNewMember (AppUser appuser ) {
        members.add(appuser);
    }

    public void changeStatus () {
        finished = !finished;
    }

    public AppUser getTheOnlyMember() {
        for (AppUser appUser: members) {
            return appUser;
        }
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto entity = (ProjectDto) o;
        return finished == entity.finished &&
                id.equals(entity.id) &&
                projectName.equals(entity.projectName) &&
                teamName.equals(entity.teamName) &&
                gitRepo.equals(entity.gitRepo) &&
                members.equals(entity.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, teamName, gitRepo, finished, members);
    }
}

