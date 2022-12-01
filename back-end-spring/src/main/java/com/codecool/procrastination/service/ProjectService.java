package com.codecool.procrastination.service;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import com.codecool.procrastination.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProjectService {
    // TODO add checks if the incoming data from frontend is complete, if not throw errors (merge dev, it has custom exceptions, and exception handling controller)
    // TODO dto layer at the end, low priority

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void saveProject (Project project, AppUser user) {
        UUID existingProjectId = exist(project.getGitRepo());
        if (existingProjectId != null) {
            project = getProjectById(existingProjectId);
        }
        project.addNewMember(user);
        projectRepository.save(project);
    }

    private UUID exist (String gitrepo) {
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

    public void changeProjectStatus(UUID id) {
        Project project = getProjectById(id);
        project.changeStatus();
        projectRepository.save(project);
    }

    public Set<Project> getProjectsByMember (UUID memberId) {
        Set<Project> memberProjects = new HashSet<>();
        List<Project> projects = projectRepository.findAll();
        for (Project project: projects) {
            if (project.findMember(memberId)) {
                memberProjects.add(project);
            }
        }
        return memberProjects;
    }
}
