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


    //checks for project by git repository
    private boolean isExistingProject(String gitRepo) {
        List<Project> projects = getAllProject();
        for (Project project:projects) {
            if(project.getGitRepo().equals(gitRepo)) {
                return true;
            }
        }
        return false;
    }

    private void deleteAbandonedProject(Project project) {
        projectRepository.delete(project);
    }


    //checks necessary data for saving a new project
    private boolean isProjectDatasFullFilled(Project project) {
        return project.getProjectName() != null && project.getGitRepo() != null && project.getTeamName() != null;
    }

    private AppUser getUserById(UUID userId) {
        return appUserService.getUserById(userId);
    }

    private Project getProjectById(UUID projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.orElseThrow(() -> new CustomExceptions.WrongProjectIdException("This project doesn't exist"));
    }

    private boolean isUserAContributor(UUID userId, UUID projectId){
        AppUser user = getUserById(userId);
        Project project = getProjectById(projectId);
        return project.isUserAMember(user);
    }


    //it checks if the project is already exist or not. If it exists add the user to it if not then create new project
    public void saveProject(Project project, UUID userId) {
        AppUser user = getUserById(userId);
        if (isExistingProject(project.getGitRepo())) {
            addUserByGitRepository(project.getGitRepo(), userId);
        }
        else if (isProjectDatasFullFilled(project)) {
            project.addNewUser(user);
            projectRepository.save(project);
        } else {
            throw new CustomExceptions.MissingAttributeException("You have to fill all the field");
        }
    }

    //return a project with user validation
    public Project getProjectByIdWithAuthorization(UUID userId, UUID projectId) throws IllegalAccessException {
        if (isUserAContributor(userId, projectId)) {
            Optional<Project> project = projectRepository.findById(projectId);
            return project.orElseThrow(() -> new CustomExceptions.WrongProjectIdException("This project doesn't exist"));
        } else {
            throw new IllegalAccessException("You are trying to reach a repository what you are not part of!\n");
        }
    }

    public void changeProjectStatus(UUID userId, UUID projectId) throws IllegalAccessException {
        if (isUserAContributor(userId, projectId)) {
            Project project = getProjectByIdWithAuthorization(userId, projectId);
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

    public void addUserByGitRepository(String gitRepo, UUID userId) {
        if (isExistingProject(gitRepo)) {
            List<Project> projects = getAllProject();
            for (Project project:projects) {
                if (project.getGitRepo().equals(gitRepo)) {
                    saveProject(project, userId);
                }
            }
        } else {
            throw new CustomExceptions.WrongGitRepositoryException("There is no project with this repository");
        }
    }

    public void leaveProject(UUID projectId,UUID userId) throws IllegalAccessException {
        AppUser user = getUserById(userId);
        Project project = getProjectByIdWithAuthorization(userId, projectId);
        project.removeUser(user);
        projectRepository.save(project);
        if (project.isProjectAbandoned()) {
            deleteAbandonedProject(project);
        }
    }
}
