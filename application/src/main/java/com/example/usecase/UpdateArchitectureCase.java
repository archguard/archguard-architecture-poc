package com.example.usecase;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import com.example.usecase.common.ArchComponentConnectionDTO;
import com.example.usecase.common.ArchComponentDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class UpdateArchitectureCase {
    @Data
    public static class Request {
        @NotNull
        private Architecture.ArchStyle archStyle;

        @NotNull
        private List<ArchComponentDTO> archComponents;

        @NotNull
        private List<ArchComponentConnectionDTO> archComponentConnections;

        public static UpdateArchitectureCommand to(Request request) {
            UpdateArchitectureCommand command = new UpdateArchitectureCommand();

            command.setArchStyle(request.getArchStyle());
            command.setArchComponents(request.archComponents.stream()
                    .map(dto -> ArchComponent.build(dto.getId(), dto.getName(), dto.getType()))
                    .collect(Collectors.toList())
            );
            command.setArchComponentConnections(request.archComponentConnections.stream()
                    .map(dto -> ArchComponentConnection.build(dto.getSource(), dto.getTarget()))
                    .collect(Collectors.toList())
            );

            return command;
        }
    }
}
