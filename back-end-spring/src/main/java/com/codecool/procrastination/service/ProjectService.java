package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
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

    private UUID checkForExistingProjectByGitRepo(String gitrepo) {
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

    private boolean isProjectDatasFullfileld(Project project) {
        return project.getProjectName() != null && project.getGitRepo() != null && project.getTeamName() != null;
    }

    public void saveProject(Project project, AppUser user) {
        UUID existingProjectId = checkForExistingProjectByGitRepo(project.getGitRepo());
        if (existingProjectId != null) {
            project = getProjectById(existingProjectId);
        }
        if (isProjectDatasFullfileld(project)) {
            project.addNewUser(user);
            projectRepository.save(project);
        } else {
            throw new CustomExceptions.MissingAttributeException("You have to fill all the field");
        }
    }

    public Project getProjectById(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project.orElse(null);
        } else {
            throw new CustomExceptions.WrongProjectIdException("This project doesn't exist");
        }
    }

    public void changeProjectStatus(UUID projectId) {
        Project project = getProjectById(projectId);
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
        UUID existingProjectId = checkForExistingProjectByGitRepo(gitRepo);
        if (existingProjectId != null) {
            saveProject(getProjectById(existingProjectId), user);
        } else {
            throw new CustomExceptions.WrongGitRepositoryException("There is no project with this repository");
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
