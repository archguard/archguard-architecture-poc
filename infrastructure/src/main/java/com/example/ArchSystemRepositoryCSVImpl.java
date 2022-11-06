package com.example;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "csv")
@Slf4j
public class ArchSystemRepositoryCSVImpl implements ArchSystemRepository {
    @Autowired
    private ArchSystemCSVRepository archSystemCSVRepository;
    @Autowired
    private ArchitectCSVRepository architectCSVRepository;
    @Autowired
    private ArchComponentCSVRepository archComponentCSVRepository;
    @Autowired
    private ArchComponentConnectionCSVRepository archComponentConnectionCSVRepository;

    @Override
    public Optional<ArchSystem> getById(String id) {
        Optional<CSVRecord> archSystemCSVRecordOptional = archSystemCSVRepository.getById(id);
        if (archSystemCSVRecordOptional.isEmpty()) {
            return Optional.empty();
        }

       return null;

    }

    @Override
    public List<ArchSystem> findAll() {
        return List.of();
    }

    @Override
    public void save(ArchSystem archSystem) {

    }
}
