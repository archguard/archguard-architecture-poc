package com.example.domain.archsystem.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Architecture {

    private String archSystemId;

    private ArchStyle archStyle;

    private List<ArchComponent> archComponents;

    public enum ArchStyle {
        LAYERED
    }

    public static Architecture build(String archSystemId, ArchStyle archStyle, List<ArchComponent> archComponents) {
        Architecture architecture = new Architecture();

        architecture.archSystemId = archSystemId;
        architecture.archStyle = archStyle;
        architecture.archComponents = archComponents;

        return architecture;
    }
}
