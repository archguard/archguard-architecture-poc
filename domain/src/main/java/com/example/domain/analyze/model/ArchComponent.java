package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
public class ArchComponent {
    private String id;

    private String name;

    private Type type;

    public static ArchComponent build(String id, String name, ArchComponent.Type type) {
        ArchComponent archComponent = new ArchComponent();

        archComponent.id = id;
        archComponent.name = name;
        archComponent.type = type;

        return archComponent;
    }

    public enum Type {
        SERVICE, MODULE, PACKAGE, CLASS,
    }
}
