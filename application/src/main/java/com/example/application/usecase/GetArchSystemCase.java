package com.example.application.usecase;

import com.example.domain.archsystem.ArchSystem;
import lombok.Builder;
import lombok.Data;


public class GetArchSystemCase {
    @Data
    @Builder
    public static class Response {
        private String id;

        private String name;


        // todo mapstruct
        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .name(archSystem.getName())
                    .build();
        }
    }
}
