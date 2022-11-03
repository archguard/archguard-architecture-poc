package com.example.domain.analyze.model;

import java.util.List;
import java.util.stream.Collectors;

public class LayeredArchitecture extends Architecture{
    public List<ArchComponent> getLayers() {
        return this.getArchComponents().stream()
                .filter(archComponent -> archComponent.getType().equals(ArchComponent.Type.PACKAGE))
                .collect(Collectors.toList());
    }
}
