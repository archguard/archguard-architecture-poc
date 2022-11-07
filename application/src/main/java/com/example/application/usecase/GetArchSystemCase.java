package com.example.application.usecase;

import com.example.domain.analyze.model.ArchSystem;
import com.example.application.usecase.common.ArchitectureDTO;
import lombok.Builder;
import lombok.Data;


public class GetArchSystemCase {
    @Data
    @Builder
    public static class Response {
        private String id;

        private String name;

        private ArchitectureDTO architecture;

        // todo mapstruct
        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .name(archSystem.getName())
                    .architecture(ArchitectureDTO.from(archSystem.getArchitecture()))
                    .build();
        }
    }
}
