package com.example.po;

import com.example.domain.analyze.model.ArchComponent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "arch_component")
public class ArchComponentPO {
    private String archSystemId;

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ArchComponent.Type type;
}
