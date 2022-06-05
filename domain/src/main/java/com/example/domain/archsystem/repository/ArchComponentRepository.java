package com.example.domain.archsystem.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArchComponentRepository {
    private final List<ArchComponentPO> archComponentPOs = new ArrayList<>();

    public List<ArchComponentPO> getByArchSystemId(String archSystemId) {
        return archComponentPOs.stream()
                .filter(archComponentPO -> archComponentPO.getArchSystemId().equals(archSystemId))
                .collect(Collectors.toList());
    }

    public ArchComponentPO create(ArchComponentPO architecturePO) {
        archComponentPOs.add(architecturePO);

        return architecturePO;
    }
}
