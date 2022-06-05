package com.example.api.usecase;

import com.example.api.usecase.common.ArchComponentRequest;
import com.example.api.usecase.common.ArchComponentResponse;
import com.example.domain.archsystem.model.ArchSystem;
import com.example.domain.archsystem.model.Architecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;


public class UpdateArchitectureCase {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private Architecture.ArchStyle archStyle;

        private List<ArchComponentRequest> archComponents;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String id;
        private Architecture architecture;
        private List<ArchComponentResponse> archComponents;

        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .architecture(archSystem.getArchitecture())
                    .archComponents(archSystem.getArchitecture().getArchComponents().stream()
                            .map(ArchComponentResponse::from)
                            .collect(Collectors.toList())
                    )
                    .build();
        }
    }
}
