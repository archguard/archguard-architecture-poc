package com.example.api.usecase.common;

import com.example.domain.archsystem.model.ArchComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArchComponentRequest {
    private String name;

    private ArchComponent.ArchComponentType archComponentType;
}
