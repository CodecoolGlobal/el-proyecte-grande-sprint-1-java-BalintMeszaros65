package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void saveProject (Project project) {
        projectRepository.save(project);
    }

    public UUID exist (String gitrepo) {
        List<Project> projects = projectRepository.findAll();
        for (Project project:projects) {
            if(project.getGitRepo().equals(gitrepo)) {
                return project.getId();
            }
        }
        return null;
    }

    public Project getProjectById (UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }

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
