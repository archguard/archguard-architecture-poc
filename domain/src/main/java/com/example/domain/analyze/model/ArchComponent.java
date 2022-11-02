package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;

@Getter
@Entity
public class ArchComponent {
    private String id;

    private String name;

    private ArchComponentType archComponentType;

    public enum ArchComponentType {
        MODULE
    }
}
