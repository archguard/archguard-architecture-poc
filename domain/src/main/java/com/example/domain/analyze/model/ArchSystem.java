package com.example.domain.analyze.model;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@ToString
@FieldNameConstants
public class ArchSystem {
    private String id;


    private String name;

    @OneToOne
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
