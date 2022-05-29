package com.example.domain.archsystem.service;

import com.example.domain.archsystem.exception.ArchSystemException;
import com.example.domain.archsystem.model.ArchSystem;
import com.example.domain.archsystem.repository.ArchSystemPO;
import com.example.domain.archsystem.repository.ArchSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchSystemService {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    public ArchSystem create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        archSystemRepository.save(ArchSystemPO.from(archSystem));

        return archSystem;
    }

    public ArchSystem get(String id) {
        ArchSystemPO archSystemPO = archSystemRepository.getById(id).orElseThrow(ArchSystemException::notFound);

        return ArchSystem.build(archSystemPO);
    }
}
