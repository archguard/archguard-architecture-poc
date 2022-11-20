package com.example.repository;

import com.example.po.ArchComponentPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchComponentCrudRepository extends CrudRepository<ArchComponentPO, String> {
    List<ArchComponentPO> findAllByArchSystemId(String archSystemId);
}
