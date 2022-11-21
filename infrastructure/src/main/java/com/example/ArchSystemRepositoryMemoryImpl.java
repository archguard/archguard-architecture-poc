package com.example;

import com.example.converter.ArchSystemConverter;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import com.example.po.ArchComponentConnectionPO;
import com.example.po.ArchComponentPO;
import com.example.po.ArchSystemPO;
import com.example.po.ArchitecturePO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "memory")
public class ArchSystemRepositoryMemoryImpl implements ArchSystemRepository {
    private final Map<String, ArchSystemPO> archSystemRepository = new HashMap<>();
    private final Map<String, ArchitecturePO> architectureRepository = new HashMap<>();
    private final Map<String, ArchComponentPO> archComponentRepository = new HashMap<>();
    private final Map<String, ArchComponentConnectionPO> archComponentConnectionRepository = new HashMap<>();

    @Override
    public Optional<ArchSystem> getById(String id) {
        ArchSystemPO archSystemPO = archSystemRepository.get(id);
        if (archSystemPO == null) {
            return Optional.empty();
        }
        return Optional.of(ArchSystemConverter.INSTANCE.from(archSystemPO));
    }

    @Override
    public String save(ArchSystem archSystem) {
        ArchSystemPO archSystemPO = ArchSystemConverter.INSTANCE.to(archSystem);
        archSystemRepository.put(archSystem.getId(), archSystemPO);
        return archSystem.getId();
    }

    @Override
    public void delete(ArchSystem archSystem) {
        String archSystemId = archSystem.getId();
        archSystemRepository.remove(archSystemId);
        architectureRepository.remove(archSystemId);

        List<String> archComponentIds = archComponentRepository.values().stream()
                .filter(archComponentPO -> archComponentPO.getArchSystemId().equals(archSystemId))
                .map(ArchComponentPO::getId).collect(Collectors.toList());
        for (String archComponentId : archComponentIds) {
            archComponentRepository.remove(archComponentId);
        }

        List<String> archComponentConnectionIds = archComponentConnectionRepository.values().stream()
                .filter(archComponentConnectionPO -> archComponentConnectionPO.getArchSystemId().equals(archSystemId))
                .map(ArchComponentConnectionPO::getArchSystemId).collect(Collectors.toList());
        for (String archComponentConnectionId : archComponentConnectionIds) {
            archComponentConnectionRepository.remove(archComponentConnectionId);
        }

    }

    @Override
    public Architecture getArchitecture(String id) {
        ArchitecturePO architecturePO = architectureRepository.get(id);
        return ArchSystemConverter.INSTANCE.from(architecturePO);
    }

    @Override
    public void updateArchitecture(String id, Architecture architecture) {
        ArchitecturePO architecturePO = ArchSystemConverter.INSTANCE.to(architecture);
        architecturePO.setArchSystemId(id);
        architectureRepository.put(id, architecturePO);
    }

    @Override
    public List<ArchComponent> getArchComponents(String id) {
        return archComponentRepository.values().stream()
                .filter(archComponentPO -> archComponentPO.getArchSystemId().equals(id))
                .map(ArchSystemConverter.INSTANCE::from).collect(Collectors.toList());
    }

    @Override
    public void addArchComponents(String id, List<ArchComponent> archComponents) {
        List<ArchComponentPO> archComponentPOList = archComponents.stream()
                .map(ArchSystemConverter.INSTANCE::to)
                .collect(Collectors.toList());

        for (ArchComponentPO archComponentPO : archComponentPOList) {
            archComponentPO.setArchSystemId(id);
            archComponentRepository.put(archComponentPO.getId(), archComponentPO);
        }
    }

    @Override
    public void updateArchComponent(String id, ArchComponent archComponent) {
        String archComponentId = archComponent.getId();
        ArchComponentPO archComponentPO = ArchSystemConverter.INSTANCE.to(archComponent);
        archComponentPO.setArchSystemId(id);
        archComponentRepository.put(archComponentId, archComponentPO);
    }

    @Override
    public void deleteArchComponent(String id, ArchComponent archComponent) {
        String archComponentId = archComponent.getId();
        archComponentRepository.remove(archComponentId);
    }

    @Override
    public List<ArchComponentConnection> getArchComponentConnections(String id) {
        return archComponentConnectionRepository.values().stream()
                .filter(archComponentConnectionPO -> archComponentConnectionPO.getArchSystemId().equals(id))
                .map(ArchSystemConverter.INSTANCE::from)
                .collect(Collectors.toList());
    }

    @Override
    public void addArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections) {
        List<ArchComponentConnectionPO> archComponentConnectionPOs =
                archComponentConnections.stream().map(ArchSystemConverter.INSTANCE::to).collect(Collectors.toList());

        for (ArchComponentConnectionPO archComponentConnectionPO : archComponentConnectionPOs) {
            archComponentConnectionPO.setArchSystemId(id);
            archComponentConnectionRepository.put(archComponentConnectionPO.getId(), archComponentConnectionPO);
        }
    }

    @Override
    public void deleteArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections) {
        for (ArchComponentConnection archComponentConnection : archComponentConnections) {
            deleteArchComponentConnection(id, archComponentConnection);
        }
    }

    private void deleteArchComponentConnection(String id, ArchComponentConnection archComponentConnection) {
        ArchComponentConnectionPO toBeDeleted = null;

        Collection<ArchComponentConnectionPO> archComponentConnectionPOS = archComponentConnectionRepository.values();
        for (ArchComponentConnectionPO archComponentConnectionPO : archComponentConnectionPOS) {
            if (archComponentConnectionPO.equals(id, archComponentConnection.getSource(),
                    archComponentConnection.getTarget())) {
                toBeDeleted = archComponentConnectionPO;
                break;
            }
        }

        if (toBeDeleted != null) {
            archComponentConnectionRepository.remove(toBeDeleted.getId());
        }
    }
}
