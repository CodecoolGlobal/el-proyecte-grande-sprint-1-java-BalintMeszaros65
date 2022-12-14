package com.codecool.procrastination.service;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.repositories.ProjectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectMessageService {

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
        List<ProjectMessage> projectMessages = projectMessageRepository.findAllByProjectIdOrderByTimestamp(projectId);
        return projectMessages.stream()
                .map(ProjectMessage::toString)
                .collect(Collectors.toList());
    }

    public void saveProjectMessage(UUID projectId, UUID userId,  ProjectMessage projectMessage) {
        Project project = projectService.getProjectById(projectId);
        AppUser appUser = appUserService.getUserById(userId);
        if (projectMessage.getMessage() == null) {
            throw new CustomExceptions.MissingAttributeException("Missing message.\n");
        } else {
            projectMessage.setProject(project);
            projectMessage.setAppUser(appUser);
            projectMessageRepository.save(projectMessage);
        }
    }
}
