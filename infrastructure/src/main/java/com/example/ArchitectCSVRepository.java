package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchitectCSVRepository extends CSVRepositoy{
    @Override
    protected String getFilePath() {
        return "architect.csv";
    }

    @Override
    protected CSVFormat getCsvFormat() {
        return CSVFormat.EXCEL.builder()
                .setHeader("arch_system_id", "name", "type")
                .build();
    }

    @Override
    protected List<String> getAggregatePath() {
        return List.of("arch_system_id");
    }

    @Override
    protected List<String> getIdPath() {
        return List.of("arch_system_id");
    }
}
