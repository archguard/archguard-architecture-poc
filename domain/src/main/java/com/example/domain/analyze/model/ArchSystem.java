package com.example.domain.analyze.model;

import com.example.domain.analyze.repository.ArchSystemPO;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ArchSystem {
    private String id;

    private String name;

    private Architecture architecture;

    private Instant createdAt;

    private Instant updatedAt;

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
        this.updatedAt = Instant.now();
    }

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
