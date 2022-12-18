package com.example.domain.analyze.repository;

import java.util.Optional;
import com.example.domain.analyze.model.ArchSystem;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchSystemRepository {
    Optional<ArchSystem> getById(String id);

    String save(ArchSystem archSystem);
}
