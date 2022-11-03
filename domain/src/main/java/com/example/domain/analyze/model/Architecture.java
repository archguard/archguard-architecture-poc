package com.example.domain.analyze.model;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.common.Entity;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Architecture {
    private ArchStyle archStyle;

    private List<ArchComponent> archComponents;

    private List<ArchComponentConnection> archComponentConnections;

    private ArchLinter archLinter;

    public enum ArchStyle {
        LAYERED
    }

    public static Architecture build(UpdateArchitectureCommand command) {
        Architecture architecture = new Architecture();

        architecture.archStyle = command.getArchStyle();
        // todo check archComponents and archComponentConnections match
        architecture.archComponents = command.getArchComponents();
        architecture.archComponentConnections = command.getArchComponentConnections();

        return architecture;
    }
}
