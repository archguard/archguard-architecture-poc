package com.example.domain.archsystem.model;

import lombok.Getter;

@Getter
public class ArchComponentConnection {
    private String archSystemId;

    private String source;

    private String target;

    public static ArchComponentConnection build(String archSystemId, String source, String target) {
        ArchComponentConnection archComponentConnection = new ArchComponentConnection();

        archComponentConnection.archSystemId = archSystemId;
        archComponentConnection.source = source;
        archComponentConnection.target = target;

        return archComponentConnection;
    }
}
