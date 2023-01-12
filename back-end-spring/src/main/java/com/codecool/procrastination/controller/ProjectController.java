package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin
    @GetMapping
    public List<Project> getUserProjects() {
        return projectService.getAllProjectsByUser();
    }

    @CrossOrigin
    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable UUID projectId) {
        return projectService.getProjectById(projectId);
    }

    @CrossOrigin
    @GetMapping("/leave/{projectId}")
    public void leaveProject(@PathVariable UUID projectId) {
        projectService.leaveProject(projectId);
    }

    @CrossOrigin
    @PostMapping
    public void addOrJoinProject(@RequestBody Project project) {
        projectService.addNewProject(project);
    }

    @CrossOrigin
    @PutMapping
    public void updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
    }
}
