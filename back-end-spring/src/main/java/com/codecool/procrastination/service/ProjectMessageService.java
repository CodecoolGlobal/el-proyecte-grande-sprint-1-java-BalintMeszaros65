package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.repositories.ProjectMessageRepository;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectMessageService {

    Logger log = LoggerFactory.getLogger(ProjectMessageService.class);

    private final ProjectMessageRepository projectMessageRepository;
    private final ProjectService projectService;
    private final AppUserService appUserService;

    @Autowired
    public ProjectMessageService(ProjectMessageRepository projectMessageRepository, ProjectService projectService, AppUserService appUserService) {
        this.projectMessageRepository = projectMessageRepository;
        this.projectService = projectService;
        this.appUserService = appUserService;
    }

    public List<String> getAllMessagesByProjectIdOrderedByTimestamp(UUID projectId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUserService.getUserByEmail(email);
        // TODO authenticate from context if the user is a member of the project
        List<ProjectMessage> projectMessages = projectMessageRepository.getAllByProjectIdOrderByTimestamp(projectId);
        return projectMessages.stream()
                .map(ProjectMessage::toString)
                .collect(Collectors.toList());
    }

    public void saveProjectMessage(UUID projectId, ProjectMessage projectMessage) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Project project = projectService.getProjectById(projectId);
        AppUser appUser = appUserService.getUserByEmail(email);
        // TODO authenticate from context if the user is a member of the project
        if (projectMessage.getMessage() == null) {
            throw new CustomExceptions.MissingAttributeException("Missing message.\n");
        } else {
            projectMessage.setProject(project);
            projectMessage.setAppUser(appUser);
            projectMessageRepository.save(projectMessage);
        }
    }
}
