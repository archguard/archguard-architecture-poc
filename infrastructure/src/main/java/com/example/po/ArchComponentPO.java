package com.example.po;

import com.example.domain.analyze.model.ArchComponent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ArchComponentPO {
    private String archSystemId;
    @Id
    private String id;

    private String name;

    private ArchComponent.Type type;
}
