package com.example.domain.analyze.service;

import java.util.List;
import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchitectureService {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    public Architecture getById(String id) {
        Architecture architecture = archSystemRepository.getArchitecture(id);
        if (architecture != null) {
            List<ArchComponent> archComponents = archSystemRepository.getArchComponents(id);
            List<ArchComponentConnection> archComponentConnections =
                    archSystemRepository.getArchComponentConnections(id);
            architecture.setArchComponents(archComponents);
            architecture.setArchComponentConnections(archComponentConnections);
        }

        return architecture;
    }

    public void create(String archSystemId, UpdateArchitectureCommand command) {
        Architecture architecture = Architecture.build(command);

        archSystemRepository.updateArchitecture(archSystemId, architecture);
        archSystemRepository.addArchComponents(archSystemId, architecture.getArchComponents());
        archSystemRepository.addArchComponentConnections(archSystemId, architecture.getArchComponentConnections());
    }

}
