package com.codecool.procrastination.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.net.URL;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String projectName;
    private String teamName;
    private URL gitRepo;
    private boolean finished;
    @ManyToMany
    private Set<AppUser> members;

    public Project() {
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

    public URL getGitRepo() {
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
}
