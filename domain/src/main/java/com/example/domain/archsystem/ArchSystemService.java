package com.example.domain.archsystem;

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
        ArchSystem archSystem = _getById(id);
        return archSystem;
    }

    public String create(String name) {
        ArchSystem archSystem = ArchSystem.build(name);

        return archSystemRepository.save(archSystem);
    }

}
