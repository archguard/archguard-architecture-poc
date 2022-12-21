package com.example.domain.archsystem;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchSystemRepository {
    Optional<ArchSystem> getById(String id);

    String save(ArchSystem archSystem);
}
