package com.example.domain.analyze.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
