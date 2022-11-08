package com.example.repository;

import com.example.po.ArchComponentConnectionPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchComponentConnectionRepository extends CrudRepository<ArchComponentConnectionPO, String> {
}
