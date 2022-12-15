package com.codecool.procrastination.service;

import com.codecool.procrastination.model.ProjectMessage;
import com.codecool.procrastination.repositories.ProjectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectMessageService {

    ProjectMessageRepository projectMessageRepository;

    @Autowired
    public ProjectMessageService(ProjectMessageRepository projectMessageRepository) {
        this.projectMessageRepository = projectMessageRepository;
    }

    public List<ProjectMessage> getAllMessagesByProjectIdOrderedByTimestamp(UUID projectId) {
        return projectMessageRepository.findAllByProjectIdOrderByTimestamp(projectId);
    }

    public void saveProjectMessage(ProjectMessage projectMessage) {
        projectMessageRepository.save(projectMessage);
    }

    public void deleteAllProjectMessage (UUID projectId) {
        List<ProjectMessage> projectMessages = projectMessageRepository.findAllByProjectIdOrderByTimestamp(projectId);
        projectMessageRepository.deleteAll(projectMessages);

    }
}
