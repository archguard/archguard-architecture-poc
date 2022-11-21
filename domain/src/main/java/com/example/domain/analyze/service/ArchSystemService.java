package com.example.domain.analyze.service;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.exception.ArchSystemException;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchSystemService {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    private ArchSystem _getById(String id) {
        return archSystemRepository.getById(id).orElseThrow(ArchSystemException::notFound);
    }
    public ArchSystem getById(String id) {
        return _getById(id);
    }

    public String create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        return archSystemRepository.save(archSystem);
    }

    public void updateArchitecture(String id, UpdateArchitectureCommand command) {
        ArchSystem archSystem = _getById(id);

        archSystem.updateArchitecture(command);

        archSystemRepository.updateArchitecture(id, archSystem.getArchitecture());
    }

}
