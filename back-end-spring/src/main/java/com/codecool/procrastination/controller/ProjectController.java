package com.codecool.procrastination.controller;

import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/save/{user_id}")
    public void saveProject(@PathVariable UUID user_id, @RequestBody Project project) throws IllegalAccessException {
        projectService.saveProject(project, user_id);
    }

    @PostMapping("/add_user/{user_id}")
    public void addUserByRepository(@PathVariable UUID user_id ,@RequestBody String gitRepository) throws IllegalAccessException {
        projectService.addUserByGitRepository(gitRepository, user_id);
    }

    @PutMapping("/change_status/{user_id}/{project_id}")
    public void changeProjectStatus(@PathVariable UUID user_id, @PathVariable UUID project_id) throws IllegalAccessException {
            projectService.changeProjectStatus(user_id, project_id);
    }

    @GetMapping("/{user_id}/{project_id}")
    public Project getProjectById(@PathVariable UUID user_id, @PathVariable UUID project_id) throws IllegalAccessException {
        return projectService.getProjectById(user_id, project_id);
    }

    @GetMapping("/{user_id}")
    public Set<Project> getUserProjects (@PathVariable UUID user_id) {
        return  projectService.getProjectsByUser(user_id);
    }

    @DeleteMapping("/leave_project/{user_id}/{project_id}")
    public void leaveProject(@PathVariable UUID user_id, @PathVariable UUID project_id) throws IllegalAccessException {
        projectService.leaveProject(project_id, user_id);
    }
}
