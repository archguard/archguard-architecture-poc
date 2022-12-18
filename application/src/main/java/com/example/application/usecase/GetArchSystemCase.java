package com.example.application.usecase;

import com.example.domain.analyze.model.ArchSystem;
import com.example.application.usecase.common.ArchitectureDTO;
import com.example.domain.analyze.model.Architecture;
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
        public static Response from(ArchSystem archSystem, Architecture architecture) {
            return Response.builder()
                    .id(archSystem.getId())
                    .name(archSystem.getName())
                    .architecture(ArchitectureDTO.from(architecture))
                    .build();
        }
    }
}
