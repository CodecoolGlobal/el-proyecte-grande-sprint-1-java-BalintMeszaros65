package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final AppUserService appUserService;

    @Autowired
    public ProjectController(ProjectService projectService, AppUserService appUserService) {
        this.projectService = projectService;
        this.appUserService = appUserService;
    }

    @PostMapping("/{user_id}")
    public void saveProject(@PathVariable UUID user_id, @RequestBody Project project) {
        AppUser user = appUserService.getUserById(user_id);
        projectService.saveProject(project, user);
    }

    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @PutMapping("/change_status/{id}")
    public void changeProjectStatus(@PathVariable UUID id) {projectService.changeProjectStatus(id);}

    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @GetMapping("/{id}")
    public Project getProjectById (@PathVariable UUID id) {return projectService.getProjectById(id);}
}
