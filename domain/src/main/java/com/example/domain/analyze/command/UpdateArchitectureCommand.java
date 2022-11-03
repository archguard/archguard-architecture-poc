package com.example.domain.analyze.command;

import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateArchitectureCommand {
    private Architecture.ArchStyle archStyle;
    private List<ArchComponent> archComponents;
    private List<ArchComponentConnection> archComponentConnections;
}
