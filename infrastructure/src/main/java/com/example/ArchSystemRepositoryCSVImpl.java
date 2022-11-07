package com.example;

import com.example.converter.MapConverter;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

        CSVRecord archSystemCsvRecord = archSystemCSVRecordOptional.get();
        ArchSystem archSystem = MapConverter.mapToObj(archSystemCsvRecord.toMap(), ArchSystem.class);

        String archSystemId = archSystem.getId();
        Optional<CSVRecord> architectCSVRecordOptional = architectCSVRepository.getById(archSystemId);
        if (architectCSVRecordOptional.isPresent()) {
            CSVRecord architectCsvRecord = architectCSVRecordOptional.get();
            Architecture architecture = MapConverter.mapToObj(architectCsvRecord.toMap(), Architecture.class);
            MapConverter.putField(archSystem, ArchSystem.Fields.architecture, architecture);

            List<ArchComponent> archComponents = archComponentCSVRepository.findAllByAggregate(archSystemId).stream()
                    .map(csvRecord -> MapConverter.mapToObj(csvRecord.toMap(), ArchComponent.class))
                    .collect(Collectors.toList());
            MapConverter.putField(architecture, Architecture.Fields.archComponents, archComponents);

            List<ArchComponentConnection> archComponentConnections =
                    archComponentConnectionCSVRepository.findAllByAggregate(archSystemId).stream()
                            .map(csvRecord -> MapConverter.mapToObj(csvRecord.toMap(), ArchComponentConnection.class))
                            .collect(Collectors.toList());
            MapConverter.putField(architecture, Architecture.Fields.archComponentConnections, archComponentConnections);
        }

        return Optional.of(archSystem);
    }

    @Override
    public void save(ArchSystem archSystem) {
        Map<String, String> archSystemMap = MapConverter.ObjToMap(archSystem);
        archSystemCSVRepository.save(archSystemMap);

        Architecture architecture = archSystem.getArchitecture();
        if (architecture != null) {
            Map<String, String> architectureMap = MapConverter.ObjToMap(architecture);
            architectureMap.put("arch_system_id", archSystem.getId());
            architectCSVRepository.save(architectureMap);

            architecture.getArchComponents().stream()
                    .map(MapConverter::ObjToMap)
                    .forEach(map -> {
                        map.put("arch_system_id", archSystem.getId());
                        archComponentCSVRepository.save(map);
                    });

            architecture.getArchComponentConnections().stream()
                    .map(MapConverter::ObjToMap)
                    .forEach(map -> {
                        map.put("arch_system_id", archSystem.getId());
                        archComponentCSVRepository.save(map);
                    });
        }
    }
}
