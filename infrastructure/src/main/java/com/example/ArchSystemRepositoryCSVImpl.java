package com.example;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "repository-impl", havingValue = "csv")
@Slf4j
public class ArchSystemRepositoryCSVImpl implements ArchSystemRepository {
    public static String filePath = "arch-system.csv";

    public static CSVFormat csvFormat = CSVFormat.EXCEL.builder().setHeader(ArchSystem.Fields.id, ArchSystem.Fields.name).build();

    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            Object val;
            if ((val = source.get(field.getName())) != null) {
                field.setAccessible(true);
                field.set(o, val);
            }
        }
        return o;
    }

    public static Map<String, Object> ObjToMap(Object obj, Object nullValue) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = field.getName();
            Object value = field.get(obj);
            if (value == null) {
                value = nullValue;
            }
            map.put(keyName, value);
        }
        return map;
    }

    private ArchSystem toArchSystem(CSVRecord csvRecord) throws Exception {
        return mapToObj(csvRecord.toMap(), ArchSystem.class);
    }

    private Map<String, Object> toCSVRecord(ArchSystem archSystem) throws IllegalAccessException {
        return ObjToMap(archSystem, "");
    }

    @Override
    public Optional<ArchSystem> getById(String id) {
        try {
            Reader in = new FileReader(filePath);
            Iterable<CSVRecord> csvRecords = csvFormat.parse(in);
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get(ArchSystem.Fields.id).equals(id)) {
                    return Optional.of(toArchSystem(csvRecord));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            log.error("getById : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ArchSystem> findAll() {
        try {
            Reader in = new FileReader(filePath);
            Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(in);
            List<ArchSystem> archSystems = new ArrayList<>();
            for (CSVRecord csvRecord : csvRecords) {
                archSystems.add(toArchSystem(csvRecord));
            }

            return archSystems;
        } catch (Exception e) {
            log.error("findAll error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ArchSystem archSystem) {
        try {
            CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), csvFormat);
            printer.printRecord(ArchSystem.Fields.id, ArchSystem.Fields.name);
            printer.printRecord(archSystem.getId(), archSystem.getName());
            printer.close();
        } catch (Exception e) {
            log.error("save error", e);
            throw new RuntimeException(e);
        }
    }
}
