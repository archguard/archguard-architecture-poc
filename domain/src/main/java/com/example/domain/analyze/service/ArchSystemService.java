package com.example.domain.analyze.service;

import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.exception.ArchSystemException;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchSystemService {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    private ArchSystem _getById(String id) {
        return archSystemRepository.getById(id).orElseThrow(ArchSystemException::notFound);
    }
    public ArchSystem getById(String id) {
        ArchSystem archSystem = _getById(id);
        return archSystem;
    }

    public String create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        return archSystemRepository.save(archSystem);
    }

}
