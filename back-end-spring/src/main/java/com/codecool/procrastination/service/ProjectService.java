package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProjectService {
    // TODO add checks if the incoming data from frontend is complete, if not throw errors (merge dev, it has custom exceptions, and exception handling controller)
    // TODO dto layer at the end, low priority

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    private UUID checkForExistingProject(String gitrepo) {
        List<Project> projects = getAllProject();
        for (Project project:projects) {
            if(project.getGitRepo().equals(gitrepo)) {
                return project.getId();
            }
        }
        return null;
    }

    private void deleteAbandonedProject(Project project) {
        projectRepository.delete(project);
    }

    public void saveProject(Project project, AppUser user) {
        UUID existingProjectId = checkForExistingProject(project.getGitRepo());
        if (existingProjectId != null) {
            project = getProjectById(existingProjectId);
        }
        project.addNewUser(user);
        projectRepository.save(project);
    }

    public Project getProjectById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

    public void changeProjectStatus(UUID id) {
        Project project = getProjectById(id);
        project.changeStatus();
        projectRepository.save(project);
    }

    public Set<Project> getProjectsByUser(AppUser user) {
        Set<Project> userProjects = new HashSet<>();
        List<Project> projects = getAllProject();
        for (Project project: projects) {
            if (project.isUserAMember(user)) {
                userProjects.add(project);
            }
        }
        return userProjects;
    }

    public boolean isUserAContributor(AppUser user, UUID projectId) {
        Project project = getProjectById(projectId);
        return project.isUserAMember(user);
    }

    public void addUserByGitRepository(String gitRepo, AppUser user) {
        UUID existingProjectId = checkForExistingProject(gitRepo);
        if (existingProjectId != null) {
            saveProject(getProjectById(existingProjectId), user);
        }
    }

    public void leaveProject(UUID projectId, AppUser user) {
        Project project = getProjectById(projectId);
        project.removeUser(user);
        if (project.isProjectAbandoned()) {
            deleteAbandonedProject(project);
        }
    }
}
