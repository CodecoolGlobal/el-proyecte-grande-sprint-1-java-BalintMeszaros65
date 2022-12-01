package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.service.ProjectMessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProjectMessageController {

    private final ProjectMessageService projectMessageService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectMessageController(ProjectMessageService projectMessageService, ModelMapper modelMapper) {
        this.projectMessageService = projectMessageService;
        this.modelMapper = modelMapper;
    }


    // TODO dto layer?
    @GetMapping("/api/messages/{project_id}")
    public List<String> getAllMessagesByProjectIdOrderedByTimestamp(@PathVariable(name = "project_id") UUID projectId) {
        List<ProjectMessage> projectMessages = projectMessageService.getAllMessagesByProjectIdOrderedByTimestamp(projectId);
        return projectMessages.stream()
                .map(ProjectMessage::toString)
                .collect(Collectors.toList());
    }

}
