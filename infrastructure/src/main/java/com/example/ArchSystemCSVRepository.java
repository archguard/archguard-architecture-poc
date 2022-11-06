package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

@Component
public class ArchSystemCSVRepository extends CSVRepositoy{
    @Override
    protected String getFilePath() {
        return "arch_system.csv";
    }

    @Override
    protected CSVFormat getCsvFormat() {
        return CSVFormat.EXCEL.builder()
                .setHeader("id", "name")
                .build();
    }

    @Override
    protected String getIdKey() {
        return "id";
    }
}
