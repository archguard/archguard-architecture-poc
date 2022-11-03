package com.example.usecase;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.usecase.common.ArchitectureDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


public class GetArchSystemCase {
    @Data
    @Builder
    public static class Response {
        private String id;

        private String name;

        private ArchitectureDTO architecture;

        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .name(archSystem.getName())
                    .architecture(ArchitectureDTO.from(archSystem.getArchitecture()))
                    .build();
        }
    }
}
