package com.example.repository;

import java.util.Optional;
import com.example.po.ArchitecturePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectureCrudRepository extends CrudRepository<ArchitecturePO, String> {
    public Optional<ArchitecturePO> findByArchSystemId(String archSystemId);
}
