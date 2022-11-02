package com.example.domain.analyze.repository;

import com.example.domain.analyze.model.ArchSystem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchSystemRepository {
    Optional<ArchSystem> getById(String id);

    List<ArchSystem> findAll();

    void save(ArchSystem archSystem);
}
