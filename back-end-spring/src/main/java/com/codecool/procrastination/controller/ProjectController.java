package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("api/project")
public class ProjectController {
    // TODO get all Projects by AppUser id

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin
    @PostMapping()
    public void saveProject(@RequestBody Project project) {projectService.saveProject(project);}

    // TODO not needed, see ProjectService
    @PostMapping("/new_member/{id}")
    public void addNewMember(@PathVariable UUID id, @RequestBody AppUser appUser) {projectService.addNewMember(id, appUser);}

    // TODO PUT method, it is changing status not creating
    @CrossOrigin
    @PostMapping("/change_status/{id}")
    public void changeProjectStatus(@PathVariable UUID id) {projectService.changeProjectStatus(id);}

    @CrossOrigin
    @GetMapping("/{id}")
    public Project getProjectById (@PathVariable UUID id) {return projectService.getProjectById(id);}
}
