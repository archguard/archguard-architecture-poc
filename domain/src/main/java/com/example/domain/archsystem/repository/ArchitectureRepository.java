package com.example.domain.archsystem.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArchitectureRepository {
    private final Map<String, ArchitecturePO> architecturePOMap = new HashMap<>();

    public Optional<ArchitecturePO> getByArchSystemId(String archSystemId) {
        return Optional.ofNullable(architecturePOMap.get(archSystemId));
    }

    public ArchitecturePO save(ArchitecturePO architecturePO) {
        architecturePOMap.put(architecturePO.getArchSystemId(), architecturePO);

        return architecturePO;
    }
}
