package com.example.api.usecase.common;

import com.example.domain.archsystem.model.ArchComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchComponentResponse {
    private String name;

    private ArchComponent.ArchComponentType archComponentType;

    public static ArchComponentResponse from(ArchComponent archComponent) {
        return ArchComponentResponse.builder()
                .name(archComponent.getName())
                .archComponentType(archComponent.getArchComponentType())
                .build();
    }
}
