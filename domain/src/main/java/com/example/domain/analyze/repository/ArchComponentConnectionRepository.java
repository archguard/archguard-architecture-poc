package com.example.domain.analyze.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArchComponentConnectionRepository {
    private final List<ArchComponentConnectionPO> archComponentConnectionPOs = new ArrayList<>();

    public List<ArchComponentConnectionPO> getByArchSystemId(String archSystemId) {
        return archComponentConnectionPOs.stream()
                .filter(archComponentConnectionPO -> archComponentConnectionPO.getArchSystemId().equals(archSystemId))
                .collect(Collectors.toList());
    }

    public List<ArchComponentConnectionPO> saveAll(
            List<ArchComponentConnectionPO> needSavedArchComponentConnectionPOs) {
        archComponentConnectionPOs.addAll(needSavedArchComponentConnectionPOs);

        return needSavedArchComponentConnectionPOs;
    }

    public void deleteByArchSystemId(String archSystemId) {
        List<ArchComponentConnectionPO> needDeletedArchComponentConnectionPOs = archComponentConnectionPOs.stream()
                .filter(archComponentConnectionPO -> archComponentConnectionPO.getArchSystemId().equals(archSystemId))
                .collect(Collectors.toList());

        archComponentConnectionPOs.removeAll(needDeletedArchComponentConnectionPOs);
    }
}
