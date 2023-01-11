package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    private final AppUserService appUserService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, AppUserService appUserService) {
        this.projectRepository = projectRepository;
        this.appUserService = appUserService;
    }

    public Project getProjectById(UUID projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new CustomExceptions.MissingAttributeException("This project doesn't exist");
        }
    }

    public Set<Project> getAllProjectsByUser() {
        AppUser appUser = getCurrentAppUser();
        return projectRepository.getProjectsByMembersContains(appUser);
    }

    private AppUser getCurrentAppUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserService.getUserByEmail(email);
    }

    private boolean userHasAccess(Project project) {
        if (project.isUserAMember(getCurrentAppUser())) {
            return true;
        } else {
            throw new CustomExceptions.MissingAttributeException("This user has no rights");
        }
    }

    public void leaveProject(UUID projectId) {
        AppUser appUser = getCurrentAppUser();
        Project project = getProjectById(projectId);
        if (userHasAccess(project)) {
            project.removeUser(appUser);
            if (project.isProjectAbandoned()) {
                projectRepository.delete(project);
            } else {
                projectRepository.save(project);
            }
        }
    }

    public void addNewProject(Project project) {
        String gitRepository = project.getGitRepo();
        Optional<Project> optionalProject = projectRepository.findProjectByGitRepo(gitRepository);
        if (optionalProject.isEmpty()) {
            projectRepository.save(project);
        } else {
            UUID projectId = project.getId();
            joinProject(projectId);
        }
    }

    private void joinProject(UUID projectId) {
        Project project = getProjectById(projectId);
        AppUser appUser = getCurrentAppUser();
        if (userHasAccess(project)) {
            project.addNewUser(appUser);
            projectRepository.save(project);
        }
    }

    public void updateProject(Project project) {
        UUID projectId = project.getId();
        if (userHasAccess(project) && projectRepository.existsById(projectId)) {
            projectRepository.save(project);
        } else {
            throw new CustomExceptions.MissingAttributeException("This user doesn't have access to this project");
        }
    }
}
