package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
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
