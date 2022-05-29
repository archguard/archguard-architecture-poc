package com.example.domain.archsystem.model;

import lombok.Getter;

@Getter
public class Architecture {

    private String archSystemId;

    private ArchStyle archStyle;

    public enum ArchStyle {
        LAYERED
    }

    public static Architecture build(String archSystemId, ArchStyle archStyle) {
        Architecture architecture = new Architecture();

        architecture.archSystemId = archSystemId;
        architecture.archStyle = archStyle;

        return architecture;
    }
}
