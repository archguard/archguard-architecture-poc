package com.example.domain.archsystem.repository;

import com.example.domain.archsystem.model.ArchSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Getter
@Service
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchSystemPO {
    private String id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public static ArchSystemPO from(ArchSystem archSystem) {
        return ArchSystemPO.builder()
                .id(archSystem.getId())
                .name(archSystem.getName())
                .createdAt(archSystem.getCreatedAt())
                .updatedAt(archSystem.getUpdatedAt())
                .build();
    }
}
