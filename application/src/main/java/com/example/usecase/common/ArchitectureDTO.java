package com.example.usecase.common;

import com.example.domain.analyze.model.Architecture;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ArchitectureDTO {
    private Architecture.ArchStyle archStyle;

    private List<ArchComponentDTO> archComponents;

    private List<ArchComponentConnectionDTO> archComponentConnections;

    public static ArchitectureDTO from(Architecture architecture) {
        if (architecture == null) return null;
        return ArchitectureDTO.builder()
                .archStyle(architecture.getArchStyle())
                .archComponents(architecture.getArchComponents().stream()
                        .map(ArchComponentDTO::from)
                        .collect(Collectors.toList())
                )
                .archComponentConnections(architecture.getArchComponentConnections().stream()
                        .map(ArchComponentConnectionDTO::from)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
