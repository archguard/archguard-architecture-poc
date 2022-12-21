package com.example.domain.archsystem;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@ToString
@FieldNameConstants
public class ArchSystem {
    private String id;
    private String name;

    public static ArchSystem build(String name) {
        ArchSystem archSystem = new ArchSystem();
        archSystem.id = UUID.randomUUID().toString();
        archSystem.name = name;

        return archSystem;
    }
}
