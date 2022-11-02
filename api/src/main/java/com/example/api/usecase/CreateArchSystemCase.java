package com.example.api.usecase;

import com.example.domain.analyze.model.ArchSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class CreateArchSystemCase {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private String name;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String id;

        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .build();
        }
    }
}
