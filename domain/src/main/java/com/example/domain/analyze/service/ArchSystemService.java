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

    private ArchSystem getById(String id) {
        return archSystemRepository.getById(id).orElseThrow(ArchSystemException::notFound);
    }

    public ArchSystem create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        archSystemRepository.save(archSystem);

        return archSystem;
    }

    public void updateArchitecture(String id, UpdateArchitectureCommand command) {
        ArchSystem archSystem = getById(id);

        archSystem.updateArchitecture(command);

        archSystemRepository.save(archSystem);
    }

}
