package com.codecool.procrastination.controller;

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

    @CrossOrigin
    @PostMapping("/save/{user_id}")
    public void saveProject(@PathVariable UUID user_id, @RequestBody Project project) {
        projectService.saveProject(project, appUserService.getUserById(user_id));
    }

    @CrossOrigin
    @PostMapping("/add_user/{user_id}/{git_repository}")
    public void addUserByRepository(@PathVariable UUID user_id ,@PathVariable String git_repository) {
        projectService.addUserByGitRepository(git_repository ,appUserService.getUserById(user_id));
    }

    @CrossOrigin
    @PutMapping("/change_status/{user_id}/{project_id}")
    public void changeProjectStatus(@PathVariable UUID user_id, @PathVariable UUID project_id) throws IllegalAccessException {
        if (projectService.isUserAContributor(appUserService.getUserById(user_id), project_id)) {
            projectService.changeProjectStatus(project_id);
        } else {
            throw new IllegalAccessException("You are trying to change a repository what you are not part of!\n");
        }
    }

    @CrossOrigin
    @GetMapping("/{user_id}/{project_id}")
    public Project getProjectById(@PathVariable UUID user_id, @PathVariable UUID project_id) throws IllegalAccessException {
        if (projectService.isUserAContributor(appUserService.getUserById(user_id), project_id)) {
            return projectService.getProjectById(project_id);
        } else {
            throw new IllegalAccessException("You are trying to reach a repository what you are not part of!\n");
        }
    }

    @CrossOrigin
    @GetMapping("/{user_id}")
    public Set<Project> getUserProjects (@PathVariable UUID user_id) {
        return  projectService.getProjectsByUser(appUserService.getUserById(user_id));
    }

    @CrossOrigin
    @DeleteMapping("/leave_project/{user_id}/{project_id}")
    public void leaveProject(@PathVariable UUID user_id, @PathVariable UUID project_id) {
        projectService.leaveProject(project_id, appUserService.getUserById(user_id));
    }
}
