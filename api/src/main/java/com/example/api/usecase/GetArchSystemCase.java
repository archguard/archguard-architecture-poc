package com.example.api.usecase;

import com.example.domain.archsystem.model.ArchSystem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class GetArchSystemCase {
    @Getter
    @Setter
    @Builder
    public static class Response {
        private final String id;
        private final String name;

        public static Response from(ArchSystem archSystem) {
            return Response.builder()
                    .id(archSystem.getId())
                    .name(archSystem.getName())
                    .build();
        }
    }
}
