package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("api/project/save")
    public void saveProject(@RequestBody Project project) {projectService.saveProject(project);}


    @PostMapping("api/project/addMember")
    public void addNewMember(@RequestBody UUID id, AppUser appUser) {projectService.addNewMember(id, appUser);}
}
