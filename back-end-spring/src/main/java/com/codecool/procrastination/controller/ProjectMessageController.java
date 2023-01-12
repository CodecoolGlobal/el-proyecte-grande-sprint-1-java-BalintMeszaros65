package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.service.ProjectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProjectMessageController {

    private final ProjectMessageService projectMessageService;

    @Autowired
    public ProjectMessageController(ProjectMessageService projectMessageService) {
        this.projectMessageService = projectMessageService;
    }


    @CrossOrigin
    @GetMapping("/api/messages/load/{project_id}")
    public List<String> getAllMessagesByProjectIdOrderedByTimestamp(@PathVariable(name = "project_id") UUID projectId) {
        return projectMessageService.getAllMessagesByProjectIdOrderedByTimestamp(projectId);
    }

    @CrossOrigin
    @GetMapping("/api/messages/save/{project_id}")
    public void saveProjectMessage(@PathVariable(name = "project_id") UUID projectId,
                                   @RequestBody ProjectMessage projectMessage) {
        projectMessageService.saveProjectMessage(projectId, projectMessage);
    }

}
