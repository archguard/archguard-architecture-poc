package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;

@Getter
@Entity
public class ArchComponentConnection {
    private String source;

    private String target;

    public static ArchComponentConnection build(String source, String target) {
        ArchComponentConnection archComponentConnection = new ArchComponentConnection();

        archComponentConnection.source = source;
        archComponentConnection.target = target;

        return archComponentConnection;
    }
}
