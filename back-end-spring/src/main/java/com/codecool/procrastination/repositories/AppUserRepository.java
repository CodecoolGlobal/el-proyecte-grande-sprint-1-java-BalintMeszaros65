package com.codecool.procrastination.repositories;

import com.codecool.procrastination.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    <S extends AppUser> S save(S entity);

    Optional<AppUser> findById(UUID uuid);

    void deleteById(UUID uuid);

    boolean existsByEmail(String email);

    Optional<AppUser> findAppUserByEmail(String email);
}