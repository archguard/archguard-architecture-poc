package com.example.domain.analyze.model;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.common.BaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectureTest {

    @Test
    void should_build_architecture_fail_when_connection_source_not_in_component() {
        UpdateArchitectureCommand command = UpdateArchitectureCommand.builder()
                .archStyle(Architecture.ArchStyle.LAYERED)
                .build();

        ArchComponent archComponent = ArchComponent.build("id-1", "name-1", ArchComponent.Type.MODULE);
        command.setArchComponents(List.of(archComponent));
        ArchComponentConnection archComponentConnection = ArchComponentConnection.build("id-any", "id-1");
        command.setArchComponentConnections(List.of(archComponentConnection));

        assertThrows(BaseException.class, () -> Architecture.build("arch-system-id", command));
    }

}