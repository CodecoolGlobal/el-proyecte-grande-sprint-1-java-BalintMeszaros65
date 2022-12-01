package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.service.AppUserService;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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

    @PostMapping("/save/{user_id}")
    public void saveProject(@PathVariable UUID user_id, @RequestBody Project project) {
        AppUser user = appUserService.getUserById(user_id);
        projectService.saveProject(project, user);
    }

    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @PutMapping("/change_status/{project_id}")
    public void changeProjectStatus(@PathVariable UUID project_id) {projectService.changeProjectStatus(project_id);}

    // TODO get AppUser id from Path and check if the Project's members contains the AppUser, if not throw error
    @GetMapping("/{project_id}")
    public Project getProjectById (@PathVariable UUID project_id) {return projectService.getProjectById(project_id);}

    @GetMapping("/{user_id}")
    public Set<Project> getUserProjects (@PathVariable UUID user_id) {return  projectService.getProjectsByMemberId(user_id);}
}
