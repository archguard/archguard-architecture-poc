package com.example;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArchSystemRepositoryImpl implements ArchSystemRepository {
    private final Map<String, ArchSystem> archSystems = new HashMap<>();

    @Override
    public Optional<ArchSystem> getById(String id) {
        return Optional.ofNullable(archSystems.get(id));
    }

    @Override
    public List<ArchSystem> findAll() {
        return new ArrayList<>(archSystems.values());
    }

    @Override
    public void save(ArchSystem archSystem) {
        archSystems.put(archSystem.getId(), archSystem);
    }
}
