package com.example.domain.analyze.repository;

import com.example.domain.analyze.model.Architecture;
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
public class ArchitecturePO {

    private String archSystemId;

    private Architecture.ArchStyle archStyle;

    public static ArchitecturePO from(Architecture architecture) {
        return ArchitecturePO.builder()
                .archSystemId(architecture.getArchSystemId())
                .archStyle(architecture.getArchStyle())
                .build();
    }
}
