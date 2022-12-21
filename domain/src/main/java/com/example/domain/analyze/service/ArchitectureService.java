package com.example.domain.analyze.service;

import java.util.List;
import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchitectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchitectureService {
    @Autowired
    private ArchitectureRepository architectureRepository;

    public Architecture getByArchSystemId(String archSystemId) {
        Architecture architecture = architectureRepository.getByArchSystemId(archSystemId).orElse(null);
        if (architecture != null) {
            List<ArchComponent> archComponents = architectureRepository.getArchComponents(archSystemId);
            List<ArchComponentConnection> archComponentConnections =
                    architectureRepository.getArchComponentConnections(archSystemId);
            architecture.setArchComponents(archComponents);
            architecture.setArchComponentConnections(archComponentConnections);
        }

        return architecture;
    }

    public String create(String archSystemId, UpdateArchitectureCommand command) {
        Architecture architecture = Architecture.build(archSystemId, command);

        String id = architectureRepository.save(architecture);
        architectureRepository.addArchComponents(archSystemId, architecture.getArchComponents());
        architectureRepository.addArchComponentConnections(archSystemId, architecture.getArchComponentConnections());

        return id;
    }

}
