package com.example.application.usecase.common;

import com.example.domain.analyze.model.ArchComponent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArchComponentDTO {
    private String id;

    private String name;

    private ArchComponent.Type type;

    public static ArchComponentDTO from(ArchComponent archComponent) {
        return ArchComponentDTO.builder()
                .id(archComponent.getId())
                .name(archComponent.getName())
                .type(archComponent.getType())
                .build();
    }
}
