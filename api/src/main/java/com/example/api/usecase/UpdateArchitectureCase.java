package com.example.api.usecase;

import com.example.domain.archsystem.model.ArchSystem;
import com.example.domain.archsystem.model.Architecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class UpdateArchitectureCase {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private Architecture.ArchStyle archStyle;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String id;
        private Architecture architecture;

        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .architecture(archSystem.getArchitecture())
                    .build();
        }
    }
}
