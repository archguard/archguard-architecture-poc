package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

@Component
public class ArchComponentConnectionCSVRepository extends CSVRepositoy{
    @Override
    protected String getFilePath() {
        return "arch_component_connection.csv";
    }

    @Override
    protected CSVFormat getCsvFormat() {
        return CSVFormat.EXCEL.builder()
                .setHeader("arch_system_id", "id", "source", "target")
                .build();
    }

    @Override
    protected String getIdKey() {
        return "id";
    }
}
