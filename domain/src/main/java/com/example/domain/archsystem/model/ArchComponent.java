package com.example.domain.archsystem.model;

import lombok.Getter;

@Getter
public class ArchComponent {
    private String archSystemId;

    private String name;

    private ArchComponentType archComponentType;

    public enum ArchComponentType {
        MODULE
    }

    public static ArchComponent build(String archSystemId, String name, ArchComponentType archComponentType) {
        ArchComponent archComponent = new ArchComponent();

        archComponent.archSystemId = archSystemId;
        archComponent.name = name;
        archComponent.archComponentType = archComponentType;

        return archComponent;
    }
}
