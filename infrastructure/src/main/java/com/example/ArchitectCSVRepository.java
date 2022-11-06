package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

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
    protected String getIdKey() {
        return "arch_system_id";
    }
}
