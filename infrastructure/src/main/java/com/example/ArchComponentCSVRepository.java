package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

@Component
public class ArchComponentCSVRepository extends CSVRepositoy{
    @Override
    protected String getFilePath() {
        return "arch_component.csv";
    }

    @Override
    protected CSVFormat getCsvFormat() {
        return CSVFormat.EXCEL.builder()
                .setHeader("arch_system_id", "id", "name", "type")
                .build();
    }

    @Override
    protected String getIdKey() {
        return "id";
    }
}
