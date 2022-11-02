package com.example.domain.analyze.repository;

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

    public List<ArchComponentPO> saveAll(List<ArchComponentPO> needSavedArchComponentPOs) {
        archComponentPOs.addAll(needSavedArchComponentPOs);

        return needSavedArchComponentPOs;
    }

    public void deleteByArchSystemId(String archSystemId) {
        List<ArchComponentPO> needDeletedArchComponentPOs = archComponentPOs.stream()
                .filter(archComponentPO -> archComponentPO.getArchSystemId().equals(archSystemId))
                .collect(Collectors.toList());

        archComponentPOs.removeAll(needDeletedArchComponentPOs);
    }
}
