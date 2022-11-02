package com.example.domain.analyze.repository;

import com.example.domain.analyze.model.ArchComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchComponentPO {
    private String archSystemId;

    private String name;

    private ArchComponent.ArchComponentType archComponentType;

    public static ArchComponentPO from(ArchComponent archComponent) {
        return ArchComponentPO.builder()
                .archSystemId(archComponent.getArchSystemId())
                .name(archComponent.getName())
                .archComponentType(archComponent.getArchComponentType())
                .build();
    }

}
