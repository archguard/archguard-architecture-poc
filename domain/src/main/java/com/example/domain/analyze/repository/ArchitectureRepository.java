package com.example.domain.analyze.repository;

import java.util.List;
import java.util.Optional;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectureRepository {
    Optional<Architecture> getByArchSystemId(String archSystemId);

    String save(Architecture architecture);

    List<ArchComponent> getArchComponents(String archSystemId);

    void addArchComponents(String archSystemId, List<ArchComponent> archComponents);

    List<ArchComponentConnection> getArchComponentConnections(String archSystemId);

    void addArchComponentConnections(String archSystemId, List<ArchComponentConnection> archComponentConnections);
}
