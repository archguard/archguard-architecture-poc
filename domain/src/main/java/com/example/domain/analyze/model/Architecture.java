package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Architecture {
    private ArchStyle archStyle;

    private List<ArchComponent> archComponents;

    private List<ArchComponentConnection> archComponentConnections;

    public enum ArchStyle {
        LAYERED
    }
}
