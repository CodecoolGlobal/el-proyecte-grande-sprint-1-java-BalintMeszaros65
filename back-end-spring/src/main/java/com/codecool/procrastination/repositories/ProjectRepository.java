package com.codecool.procrastination.repositories;

import com.codecool.procrastination.model.AppUser;
import com.codecool.procrastination.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Set<Project> getProjectsByMembersContains(AppUser member);

    Optional<Project> findProjectByGitRepo(String gitRepository);
}
