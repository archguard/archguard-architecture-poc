package com.example.repository;

import com.example.po.ArchitecturePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectureCrudRepository extends CrudRepository<ArchitecturePO, String> {
}
