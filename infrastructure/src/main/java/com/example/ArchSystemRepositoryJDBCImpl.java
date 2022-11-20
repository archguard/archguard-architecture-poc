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
import com.example.repository.ArchComponentConnectionCrudRepository;
import com.example.repository.ArchComponentCrudRepository;
import com.example.repository.ArchSystemCrudRepository;
import com.example.repository.ArchitectureCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "jdbc")
public class ArchSystemRepositoryJDBCImpl implements ArchSystemRepository {
    @Autowired
    private ArchSystemCrudRepository archSystemCrudRepository;

    @Autowired
    private ArchitectureCrudRepository architectureCrudRepository;

    @Autowired
    private ArchComponentCrudRepository archComponentCrudRepository;

    @Autowired
    private ArchComponentConnectionCrudRepository archComponentConnectionCrudRepository;

    @Override
    public Optional<ArchSystem> getById(String id) {
        return archSystemCrudRepository.findById(id)
                .map(ArchSystemConverter.INSTANCE::from);
    }

    @Override
    public String save(ArchSystem archSystem) {
        ArchSystemPO archSystemPO = ArchSystemConverter.INSTANCE.to(archSystem);
        ArchSystemPO savedArchSystemPO = archSystemCrudRepository.save(archSystemPO);
        return savedArchSystemPO.getId();
    }

    @Override
    public void delete(ArchSystem archSystem) {
        String archSystemId = archSystem.getId();
        archSystemCrudRepository.deleteById(archSystemId);
        architectureCrudRepository.deleteById(archSystemId);

        List<ArchComponentPO> archComponentPOList = archComponentCrudRepository.findAllByArchSystemId(archSystemId);
        archComponentCrudRepository.deleteAll(archComponentPOList);

        List<ArchComponentConnectionPO> archComponentConnectionPOList =
                archComponentConnectionCrudRepository.findAllByArchSystemId(archSystemId);
        archComponentConnectionCrudRepository.deleteAll(archComponentConnectionPOList);
    }

    @Override
    public Architecture getArchitecture(String id) {
        return architectureCrudRepository.findById(id)
                .map(ArchSystemConverter.INSTANCE::from)
                .orElse(null);
    }

    @Override
    public void updateArchitecture(String id, Architecture architecture) {
        ArchitecturePO architecturePO = ArchSystemConverter.INSTANCE.to(architecture);
        architecturePO.setArchSystemId(id);
        architectureCrudRepository.save(architecturePO);
    }

    @Override
    public List<ArchComponent> getArchComponents(String id) {
        return archComponentCrudRepository.findAllByArchSystemId(id).stream()
                .map(ArchSystemConverter.INSTANCE::from)
                .collect(Collectors.toList());
    }

    @Override
    public void updateArchComponent(String id, ArchComponent archComponent) {
        ArchComponentPO archComponentPO = ArchSystemConverter.INSTANCE.to(archComponent);
        archComponentPO.setArchSystemId(id);
        archComponentCrudRepository.save(archComponentPO);
    }

    @Override
    public void deleteArchComponent(String id, ArchComponent archComponent) {
        String archComponentId = archComponent.getId();
        archComponentCrudRepository.deleteById(archComponentId);
    }

    @Override
    public List<ArchComponentConnection> getArchComponentConnections(String id) {
        return archComponentConnectionCrudRepository.findAllByArchSystemId(id).stream()
                .map(ArchSystemConverter.INSTANCE::from)
                .collect(Collectors.toList());
    }

    @Override
    public void addArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections) {
        List<ArchComponentConnectionPO> archComponentConnectionPOs =
                archComponentConnections.stream().map(ArchSystemConverter.INSTANCE::to).collect(Collectors.toList());

        archComponentConnectionCrudRepository.saveAll(archComponentConnectionPOs);
    }

    @Override
    public void deleteArchComponentConnections(String id, List<ArchComponentConnection> archComponentConnections) {
        for (ArchComponentConnection archComponentConnection : archComponentConnections) {
            deleteArchComponentConnection(id, archComponentConnection);
        }
    }

    private void deleteArchComponentConnection(String id, ArchComponentConnection archComponentConnection) {
        archComponentConnectionCrudRepository.findByArchSystemIdAndSourceAndTarget(id,
                        archComponentConnection.getSource(), archComponentConnection.getTarget())
                .ifPresent(
                        archComponentConnectionPO -> archComponentConnectionCrudRepository.delete(
                                archComponentConnectionPO));
    }
}
