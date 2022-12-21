package com.example.application.usecase;

import java.util.List;
import java.util.stream.Collectors;
import com.example.application.usecase.common.ArchComponentConnectionDTO;
import com.example.application.usecase.common.ArchComponentDTO;
import com.example.domain.analyze.model.Architecture;
import lombok.Builder;
import lombok.Data;


public class GetArchitectureCase {
    @Data
    @Builder
    public static class Response {
        private Architecture.ArchStyle archStyle;

        private List<ArchComponentDTO> archComponents;

        private List<ArchComponentConnectionDTO> archComponentConnections;

        public static Response from(Architecture architecture) {
            return Response.builder()
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
}
