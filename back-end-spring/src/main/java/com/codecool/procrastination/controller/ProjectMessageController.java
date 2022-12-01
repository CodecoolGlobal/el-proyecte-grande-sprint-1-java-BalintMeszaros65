package com.codecool.procrastination.controller;

import com.codecool.procrastination.exceptions.CustomExceptions;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.service.ProjectMessageService;
import com.codecool.procrastination.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProjectMessageController {

    private final ProjectMessageService projectMessageService;
    private final ProjectService projectService;
    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectMessageController(ProjectMessageService projectMessageService, ProjectService projectService, AppUserService appUserService, ModelMapper modelMapper) {
        this.projectMessageService = projectMessageService;
        this.projectService = projectService;
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    // TODO dto layer?
    @CrossOrigin
    @GetMapping("/api/messages/{project_id}")
    // TODO logic to service layer
    public List<String> getAllMessagesByProjectIdOrderedByTimestamp(@PathVariable(name = "project_id") UUID projectId) {
        List<ProjectMessage> projectMessages = projectMessageService.getAllMessagesByProjectIdOrderedByTimestamp(projectId);
        return projectMessages.stream()
                .map(ProjectMessage::toString)
                .collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping("/api/messages/{project_id}/{user_id}")
    // TODO logic to service layer
    public void saveProjectMessage(@PathVariable(name = "project_id") UUID projectId,
                                   @PathVariable(name = "user_id") UUID userId,
                                   @RequestBody ProjectMessage projectMessage) {
        AppUser appUser = appUserService.getUserById(userId);
        Project project = projectService.getProjectById(projectId);
        if (projectMessage.getMessage() == null) {
            throw new CustomExceptions.MissingAttributeException("Missing message.\n");
        } else {
            projectMessage.setProject(project);
            projectMessage.setAppUser(appUser);
            projectMessageService.saveProjectMessage(projectMessage);
        }
    }

}
