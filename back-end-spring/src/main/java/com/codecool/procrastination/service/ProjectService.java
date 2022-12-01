package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProjectService {
    // TODO get all Projects by AppUser id
    // TODO saveProject: check if it already exists and if it is then add member (get id from controller) and save it to DB
    // TODO add checks if the incoming data from frontend is complete, if not throw errors (merge dev, it has custom exceptions, and exception handling controller)
    // TODO dto layer at the end, low priority

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void saveProject (Project project) {projectRepository.save(project);}

    public Project getProjectById (UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }


    // TODO not needed, see above
    public void addNewMember (UUID id, AppUser appUser) {
        Project project = getProjectById(id);
        project.addNewMember(appUser);
        projectRepository.save(project);
    }

    public void changeProjectStatus(UUID id) {
        Project project = getProjectById(id);
        project.changeStatus();
        projectRepository.save(project);
    }
}
