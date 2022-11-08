package com.example.repository;

import com.example.po.ArchComponentPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchComponentRepository extends CrudRepository<ArchComponentPO, String> {
}
