package com.example.domain.analyze.model;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.common.Aggregate;
import lombok.Getter;

import java.util.UUID;

@Getter
@Aggregate
public class ArchSystem {
    private String id;

    private String name;

    private Architecture architecture;

    public static ArchSystem build(String name) {
        ArchSystem archSystem = new ArchSystem();

        archSystem.id = UUID.randomUUID().toString();
        archSystem.name = name;

        return archSystem;
    }

    public void updateArchitecture(UpdateArchitectureCommand command) {
        this.architecture = Architecture.build(command);
    }
}
