package com.example;

import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Component;

import java.util.List;

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
    protected List<String> getAggregatePath() {
        return List.of();
    }

    @Override
    protected List<String> getIdPath() {
        return List.of("id");
    }

}
