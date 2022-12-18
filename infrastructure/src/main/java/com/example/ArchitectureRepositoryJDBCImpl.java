package com.example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.converter.ArchSystemConverter;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import com.example.domain.analyze.repository.ArchitectureRepository;
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

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "jdbc")
public class ArchitectureRepositoryJDBCImpl implements ArchitectureRepository {

    @Autowired
    private ArchitectureCrudRepository architectureCrudRepository;

    @Autowired
    private ArchComponentCrudRepository archComponentCrudRepository;

    @Autowired
    private ArchComponentConnectionCrudRepository archComponentConnectionCrudRepository;

    @Override
    public Optional<Architecture> getByArchSystemId(String archSystemId) {
        return architectureCrudRepository.findByArchSystemId(archSystemId)
                .map(ArchSystemConverter.INSTANCE::from);
    }

    @Override
    public String save(Architecture architecture) {
        ArchitecturePO architecturePO = ArchSystemConverter.INSTANCE.to(architecture);
        ArchitecturePO savedArchitecturePO = architectureCrudRepository.save(architecturePO);
        return savedArchitecturePO.getId();
    }

    @Override
    public List<ArchComponent> getArchComponents(String archSystemId) {
        return archComponentCrudRepository.findAllByArchSystemId(archSystemId).stream()
                .map(ArchSystemConverter.INSTANCE::from)
                .collect(Collectors.toList());
    }

    @Override
    public void addArchComponents(String archSystemId, List<ArchComponent> archComponents) {
        List<ArchComponentPO> archComponentPOList = archComponents.stream()
                .map(ArchSystemConverter.INSTANCE::to)
                .collect(Collectors.toList());

        archComponentPOList.forEach(archComponentPO -> archComponentPO.setArchSystemId(archSystemId));

        archComponentCrudRepository.saveAll(archComponentPOList);
    }

    @Override
    public List<ArchComponentConnection> getArchComponentConnections(String id) {
        return archComponentConnectionCrudRepository.findAllByArchSystemId(id).stream()
                .map(ArchSystemConverter.INSTANCE::from)
                .collect(Collectors.toList());
    }

    @Override
    public void addArchComponentConnections(String archSystemId, List<ArchComponentConnection> archComponentConnections) {
        List<ArchComponentConnectionPO> archComponentConnectionPOs = archComponentConnections.stream()
                .map(ArchSystemConverter.INSTANCE::to)
                .collect(Collectors.toList());

        archComponentConnectionPOs.forEach(archComponentConnectionPO -> {
            archComponentConnectionPO.setArchSystemId(archSystemId);
            archComponentConnectionPO.setId(UUID.randomUUID().toString());
        });

        archComponentConnectionCrudRepository.saveAll(archComponentConnectionPOs);
    }
}
