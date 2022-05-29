package com.example.domain.archsystem.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArchSystemRepository {
    private final Map<String, ArchSystemPO> archSystemPOMap = new HashMap<>();

    public Optional<ArchSystemPO> getById(String id) {
        return Optional.ofNullable(archSystemPOMap.get(id));
    }

    public ArchSystemPO save(ArchSystemPO archSystemPO) {
        archSystemPOMap.put(archSystemPO.getId(), archSystemPO);

        return archSystemPO;
    }
}
