package com.example.domain.analyze.model;

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

    public static ArchSystem build(String name) {
        ArchSystem archSystem = new ArchSystem();

        archSystem.id = UUID.randomUUID().toString();
        archSystem.name = name;
        archSystem.createdAt = Instant.now();
        archSystem.updatedAt = Instant.now();

        return archSystem;
    }
}
