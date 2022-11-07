package com.example.domain.analyze.model;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.exception.ArchSystemException;
import com.example.domain.common.Entity;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Entity
@ToString
@FieldNameConstants
public class Architecture {
    private ArchStyle archStyle;

    private List<ArchComponent> archComponents;

    private List<ArchComponentConnection> archComponentConnections;

    private ArchLinter archLinter;

    public static Architecture build(UpdateArchitectureCommand command) {
        checkArchComponentConnectionAvailable(command.getArchComponents(), command.getArchComponentConnections());

        Architecture architecture = new Architecture();

        architecture.archStyle = command.getArchStyle();
        architecture.archComponents = command.getArchComponents();
        architecture.archComponentConnections = command.getArchComponentConnections();

        return architecture;
    }

    private static void checkArchComponentConnectionAvailable(List<ArchComponent> components,
                                                              List<ArchComponentConnection> archComponentConnections) {
        Set<String> archComponentIds = components.stream()
                .map(ArchComponent::getId)
                .collect(Collectors.toSet());

        Set<String> sourceAndTargetComponentIds = archComponentConnections.stream()
                .flatMap(archComponentConnection ->
                        Stream.of(archComponentConnection.getSource(), archComponentConnection.getTarget())
                )
                .collect(Collectors.toSet());

        if (!archComponentIds.containsAll(sourceAndTargetComponentIds)) {
            throw ArchSystemException.archComponentConnectionUnavailable();
        }


    }

    public enum ArchStyle {
        LAYERED, DDD, MICROSERVICE
    }
}
