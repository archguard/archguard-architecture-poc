package com.example.usecase.common;

import com.example.domain.analyze.model.ArchComponent;
import lombok.Data;

@Data
public class ArchComponentDTO {
    private String id;

    private String name;

    private ArchComponent.Type type;
}
