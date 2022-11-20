package com.example.repository;

import com.example.po.ArchComponentConnectionPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchComponentConnectionCrudRepository extends CrudRepository<ArchComponentConnectionPO, String> {
    List<ArchComponentConnectionPO> findAllByArchSystemId(String archSystemId);

    Optional<ArchComponentConnectionPO> findByArchSystemIdAndSourceAndTarget(String archSystemId, String source, String target);
}
