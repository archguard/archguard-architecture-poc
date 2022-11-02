package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Architecture {

    private String archSystemId;

    private ArchStyle archStyle;

    private List<ArchComponent> archComponents;

    private List<ArchComponentConnection> archComponentConnections;

    public enum ArchStyle {
        LAYERED
    }

    public static Architecture build(String archSystemId, ArchStyle archStyle, List<ArchComponent> archComponents,
                                     List<ArchComponentConnection> archComponentConnections) {
        Architecture architecture = new Architecture();

        architecture.archSystemId = archSystemId;
        architecture.archStyle = archStyle;
        architecture.archComponents = archComponents;
        architecture.archComponentConnections = archComponentConnections;

        return architecture;
    }
}
