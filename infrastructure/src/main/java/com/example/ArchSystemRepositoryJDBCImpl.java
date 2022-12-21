package com.example;

import java.util.Optional;
import com.example.converter.ArchSystemConverter;
import com.example.domain.archsystem.ArchSystem;
import com.example.domain.archsystem.ArchSystemRepository;
import com.example.po.ArchSystemPO;
import com.example.repository.ArchSystemCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "jdbc")
public class ArchSystemRepositoryJDBCImpl implements ArchSystemRepository {
    @Autowired
    private ArchSystemCrudRepository archSystemCrudRepository;

    @Override
    public Optional<ArchSystem> getById(String id) {
        return archSystemCrudRepository.findById(id)
                .map(ArchSystemConverter.INSTANCE::from);
    }

    @Override
    public String save(ArchSystem archSystem) {
        ArchSystemPO archSystemPO = ArchSystemConverter.INSTANCE.to(archSystem);
        ArchSystemPO savedArchSystemPO = archSystemCrudRepository.save(archSystemPO);
        return savedArchSystemPO.getId();
    }
}
