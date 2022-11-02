package com.example.domain.analyze.service;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchSystemService {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    public ArchSystem create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        archSystemRepository.save(archSystem);

        return archSystem;
    }

}
