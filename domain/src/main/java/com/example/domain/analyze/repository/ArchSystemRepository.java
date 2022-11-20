package com.example.domain.analyze.repository;

import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchSystemRepository {
    Optional<ArchSystem> getById(String id);
    String save(ArchSystem archSystem);
    void delete(ArchSystem archSystem);

    Architecture getArchitecture(String id);
    void updateArchitecture(String id, Architecture architecture);

    List<ArchComponent> getArchComponents(String id);
    void updateArchComponent(String id, ArchComponent archComponent);
    void deleteArchComponent(String id, ArchComponent archComponent);

    List<ArchComponentConnection> getArchComponentConnections(String id);
    void addArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections);
    void deleteArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections);
}
