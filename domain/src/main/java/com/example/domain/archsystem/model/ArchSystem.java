package com.example.domain.archsystem.model;

import com.example.domain.archsystem.repository.ArchSystemPO;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ArchSystem {
    private String id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public static ArchSystem build(String name) {
        ArchSystem archSystem = new ArchSystem();

        archSystem.id = UUID.randomUUID().toString();
        archSystem.name = name;
        archSystem.createdAt = Instant.now();
        archSystem.updatedAt = Instant.now();

        return archSystem;
    }

    public static ArchSystem build(ArchSystemPO archSystemPO) {
        ArchSystem archSystem = new ArchSystem();

        archSystem.id = archSystemPO.getId();
        archSystem.name = archSystemPO.getName();
        archSystem.createdAt = archSystemPO.getCreatedAt();
        archSystem.updatedAt = archSystemPO.getUpdatedAt();

        return archSystem;
    }
}
