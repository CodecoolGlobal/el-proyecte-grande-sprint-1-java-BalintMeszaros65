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

    // TODO get AppUser id from Path
    @PostMapping()
    public void saveProject(@RequestBody Project project) {projectService.saveProject(project);}

    // TODO not needed, see ProjectService
    @PostMapping("/new_member/{id}")
    public void addNewMember(@PathVariable UUID id, @RequestBody AppUser appUser) {projectService.addNewMember(id, appUser);}

    // TODO PUT method, it is changing status not creating
    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @PostMapping("/change_status/{id}")
    public void changeProjectStatus(@PathVariable UUID id) {projectService.changeProjectStatus(id);}

    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @GetMapping("/{id}")
    public Project getProjectById (@PathVariable UUID id) {return projectService.getProjectById(id);}
}
