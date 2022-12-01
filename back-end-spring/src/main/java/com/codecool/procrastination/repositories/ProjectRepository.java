package com.codecool.procrastination.repositories;

import com.codecool.procrastination.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    // TODO get all Projects by AppUser id
}
