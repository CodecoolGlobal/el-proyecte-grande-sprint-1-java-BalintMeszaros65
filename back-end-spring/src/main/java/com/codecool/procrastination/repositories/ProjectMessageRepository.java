package com.codecool.procrastination.repositories;

import com.codecool.procrastination.model.ProjectMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectMessageRepository extends JpaRepository<ProjectMessage, UUID> {

    List<ProjectMessage> findAllByProjectIdOrderByTimestamp(UUID projectId);
}