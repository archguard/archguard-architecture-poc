package com.example.repository;

import com.example.po.ArchSystemPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchSystemCrudRepository extends CrudRepository<ArchSystemPO, String> {
}
