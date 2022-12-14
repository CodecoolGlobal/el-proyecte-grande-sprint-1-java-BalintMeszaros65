package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    // TODO dto layer at the end, low priority

    private final ProjectRepository projectRepository;
    private final AppUserService appUserService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, AppUserService appUserService) {
        this.projectRepository = projectRepository;
        this.appUserService = appUserService;
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

    private boolean isProjectDatasFullFilled(Project project) {
        return project.getProjectName() != null && project.getGitRepo() != null && project.getTeamName() != null;
    }

    private AppUser getUserById(UUID userId) {
        return appUserService.getUserById(userId);
    }

    private Project getProjectByIdToAuthentication(UUID projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.orElseThrow(() -> new CustomExceptions.WrongProjectIdException("This project doesn't exist"));
    }

    private boolean isUserAContributor(UUID userId, UUID projectId){
        AppUser user = getUserById(userId);
        Project project = getProjectByIdToAuthentication(projectId);
        return project.isUserAMember(user);
    }

    public void saveProject(Project project, UUID userId) throws IllegalAccessException {
        AppUser user = getUserById(userId);
        UUID existingProjectId = checkForExistingProjectByGitRepo(project.getGitRepo());
        if (existingProjectId != null) {
            project = getProjectById(userId, existingProjectId);
        }
        if (isProjectDatasFullFilled(project)) {
            project.addNewUser(user);
            projectRepository.save(project);
        } else {
            throw new CustomExceptions.MissingAttributeException("You have to fill all the field");
        }
    }

    public Project getProjectById(UUID userId, UUID projectId) throws IllegalAccessException {
        if (isUserAContributor(userId, projectId)) {
            Optional<Project> project = projectRepository.findById(projectId);
            return project.orElseThrow(() -> new CustomExceptions.WrongProjectIdException("This project doesn't exist"));
        } else {
            throw new IllegalAccessException("You are trying to reach a repository what you are not part of!\n");
        }
    }

    public void changeProjectStatus(UUID userId, UUID projectId) throws IllegalAccessException {
        if (isUserAContributor(userId, projectId)) {
            Project project = getProjectById(userId, projectId);
            project.changeStatus();
            projectRepository.save(project);
        } else {
            throw new IllegalAccessException("You are trying to change a repository what you are not part of!\n");
        }
    }

    public Set<Project> getProjectsByUser(UUID userId) {
        AppUser user = getUserById(userId);
        Set<Project> userProjects = new HashSet<>();
        List<Project> projects = getAllProject();
        for (Project project: projects) {
            if (project.isUserAMember(user)) {
                userProjects.add(project);
            }
        }
        return userProjects;
    }

    public void addUserByGitRepository(String gitRepo, UUID userId) throws IllegalAccessException {
        UUID existingProjectId = checkForExistingProjectByGitRepo(gitRepo);
        if (existingProjectId != null) {
            saveProject(getProjectById(userId, existingProjectId), userId);
        } else {
            throw new CustomExceptions.WrongGitRepositoryException("There is no project with this repository");
        }
    }

    public void leaveProject(UUID projectId,UUID userId) throws IllegalAccessException {
        AppUser user = getUserById(userId);
        Project project = getProjectById(userId, projectId);
        project.removeUser(user);
        projectRepository.save(project);
        if (project.isProjectAbandoned()) {
            deleteAbandonedProject(project);
        }
    }
}
