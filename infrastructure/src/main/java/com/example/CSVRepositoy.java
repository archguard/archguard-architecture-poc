package com.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class CSVRepositoy {
    protected abstract String getFilePath();

    protected abstract CSVFormat getCsvFormat();

    protected abstract List<String> getAggregatePath();

    protected abstract List<String> getIdPath();

    private boolean match(CSVRecord csvRecord, List<String> pathKey, String[] pathValue) {
        System.out.println("aaa");
        System.out.println(csvRecord.toMap());
        System.out.println(pathKey);
        System.out.println(pathValue);
        for (int i = 0; i < pathKey.size(); i++) {
            if (!csvRecord.get(pathKey.get(i)).equals(pathValue[i])) {
                return false;
            }
        }

        return true;
    }

    public Optional<CSVRecord> getById(String... pathValue) {
        try {
            Reader in = new FileReader(getFilePath());
            Iterable<CSVRecord> csvRecords = getCsvFormat().parse(in);
            for (CSVRecord csvRecord : csvRecords) {
                if (match(csvRecord, getIdPath(), pathValue)) {
                    return Optional.of(csvRecord);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CSVRecord> findAllByAggregate(String... pathValue) {
        try {
            Reader in = new FileReader(getFilePath());
            Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(in);

            List<CSVRecord> csvRecordList = new ArrayList<>();

            for (CSVRecord csvRecord : csvRecords) {
                if (match(csvRecord, getAggregatePath(), pathValue)) {
                    csvRecordList.add(csvRecord);
                }
            }

            return csvRecordList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Map<String, String> data) {
        try {
            CSVPrinter printer = new CSVPrinter(new FileWriter(getFilePath()), getCsvFormat());

            String[] header = getCsvFormat().getHeader();
            String[] values = new String[header.length];
            for (int i = 0; i < header.length; i++) {
                values[i] = data.getOrDefault(header[i], "");
            }

            printer.printRecord(values);
            printer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
