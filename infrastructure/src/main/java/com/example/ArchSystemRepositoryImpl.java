package com.example;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ArchSystemRepositoryImpl implements ArchSystemRepository {
    private final Map<String, ArchSystem> archSystemMap = new HashMap<>();

    @Override
    public Optional<ArchSystem> getById(String id) {
        return Optional.ofNullable(archSystemMap.get(id));
    }

    @Override
    public List<ArchSystem> findAll() {
        return new ArrayList<>(archSystemMap.values());
    }

    @Override
    public void save(ArchSystem archSystem) {
        archSystemMap.put(archSystem.getId(), archSystem);
    }
}
