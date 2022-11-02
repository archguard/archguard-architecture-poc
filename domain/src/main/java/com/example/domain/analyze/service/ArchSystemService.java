package com.example.domain.analyze.service;

import com.example.domain.analyze.exception.ArchSystemException;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchComponentConnectionPO;
import com.example.domain.analyze.repository.ArchComponentConnectionRepository;
import com.example.domain.analyze.repository.ArchComponentPO;
import com.example.domain.analyze.repository.ArchComponentRepository;
import com.example.domain.analyze.repository.ArchSystemPO;
import com.example.domain.analyze.repository.ArchSystemRepository;
import com.example.domain.analyze.repository.ArchitecturePO;
import com.example.domain.analyze.repository.ArchitectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchSystemService {
    @Autowired
    private ArchSystemRepository archSystemRepository;
    @Autowired
    private ArchitectureRepository architectureRepository;
    @Autowired
    private ArchComponentRepository archComponentRepository;
    @Autowired
    private ArchComponentConnectionRepository archComponentConnectionRepository;

    public ArchSystem create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        archSystemRepository.save(ArchSystemPO.from(archSystem));

        return archSystem;
    }

    private ArchSystem _get(String id) {
        ArchSystemPO archSystemPO = archSystemRepository.getById(id).orElseThrow(ArchSystemException::notFound);

        return ArchSystem.build(archSystemPO);
    }

    public ArchSystem get(String id) {
        return _get(id);
    }

    public ArchSystem updateArchitecture(String id, Architecture.ArchStyle archStyle,
                                         List<ArchComponent> archComponents,
                                         List<ArchComponentConnection> archComponentConnections) {
        ArchSystem archSystem = _get(id);

        Architecture architecture = Architecture.build(id, archStyle, archComponents, archComponentConnections);
        archSystem.setArchitecture(architecture);

        architectureRepository.save(ArchitecturePO.from(architecture));
        archComponentRepository.deleteByArchSystemId(id);
        archComponentRepository.saveAll(
                archComponents.stream().map(ArchComponentPO::from).collect(Collectors.toList())
        );
        archComponentConnectionRepository.deleteByArchSystemId(id);
        archComponentConnectionRepository.saveAll(
                archComponentConnections.stream().map(ArchComponentConnectionPO::from).collect(Collectors.toList())
        );
        archSystemRepository.save(ArchSystemPO.from(archSystem));

        return archSystem;
    }

}
